package com.khue.calorietracker.tracker.tracker_presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.tracker.tracker_domain.model.MealType
import com.khue.calorietracker.tracker.tracker_presentation.search.components.SearchTextField
import com.khue.calorietracker.tracker.tracker_presentation.search.components.TrackableFoodItem
import java.time.LocalDate


@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SearchScreenRoute(
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onShowSnackbar: suspend (String, String?) -> Boolean
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    onShowSnackbar(event.message.asString(context), null)
                    keyboardController?.hide()
                }

                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    SearchScreen(
        mealName = mealName,
        dayOfMonth = dayOfMonth,
        month = month,
        year = year,
        onNavigateUp = onNavigateUp,
        modifier = modifier,
        onEvent = viewModel::onEvent,
        state = viewModel.state,
        onShowSnackbar = onShowSnackbar
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit,
    modifier: Modifier,
    onEvent: (SearchEvent) -> Unit,
    state: SearchState,
    onShowSnackbar: suspend (String, String?) -> Boolean
) {

    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = state.query,
            onValueChange = {
                onEvent(SearchEvent.OnQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
                onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            }
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    spacing.spaceMedium
                ),
                contentPadding = PaddingValues(spacing.spaceMedium)
            ) {
                items(state.trackableFood) { food ->
                    TrackableFoodItem(
                        trackableFoodUiState = food,
                        onClick = {
                            onEvent(SearchEvent.OnToggleTrackableFood(food.food))
                        },
                        onAmountChange = {
                            onEvent(
                                SearchEvent.OnAmountForFoodChange(
                                    food = food.food,
                                    amount = it
                                )
                            )
                        },
                        onTrack = {
                            keyboardController?.hide()
                            onEvent(
                                SearchEvent.OnTrackFoodClick(
                                    food = food.food,
                                    mealType = MealType.fromString(mealName),
                                    date = LocalDate.of(year, month, dayOfMonth)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> {
                CircularProgressIndicator()
            }
            state.trackableFood.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}