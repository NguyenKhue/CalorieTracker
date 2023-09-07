package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components.AddButton
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components.DaySelector
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components.TrackedFoodItem


@Composable
internal fun TrackerOverviewRoute(
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    TrackerOverviewScreen(
        onNavigate = onNavigate,
        modifier = modifier,
        onEvent = viewModel::onEvent,
        state = viewModel.state
    )
}

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier,
    onEvent: (TrackerOverviewEvent) -> Unit,
    state: TrackerOverviewState
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall),
                        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                    ) {
                        state.trackedFoods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = {
                                    onEvent(TrackerOverviewEvent.OnDeleteTrackedFood(food))
                                },
                            )
                        }
                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                onEvent(TrackerOverviewEvent.OnAddFoodClick(meal))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}