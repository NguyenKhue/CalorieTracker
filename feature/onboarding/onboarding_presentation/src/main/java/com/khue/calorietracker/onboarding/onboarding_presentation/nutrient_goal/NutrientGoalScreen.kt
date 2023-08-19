package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.core.ui.DevicePreviews
import com.khue.calorietracker.onboarding.onboarding_presentation.components.ActionButton
import com.khue.calorietracker.onboarding.onboarding_presentation.components.UnitTextField


@Composable
internal fun NutrientGoalRoute(
    onNavigateToTrackerOverviewScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: NutrientGoalViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigateToTrackerOverviewScreen(null)
                is UiEvent.ShowSnackbar -> {
                    onShowSnackbar(event.message.asString(context), null)
                }
                else -> Unit
            }
        }
    }

    NutrientGoalScreen(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NutrientGoalScreen(
    modifier: Modifier,
    onEvent: (NutrientGoalEvent) -> Unit = {},
) {
    val spacing = LocalSpacing.current
    val focusManager = LocalFocusManager.current
    val (carbsFocus, proteinsFocus, fatsFocus) = remember { FocusRequester.createRefs() }

    LaunchedEffect(key1 = true) {
        carbsFocus.requestFocus()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                UnitTextField(
                    initValue = "40",
                    onValueChange = { onEvent(NutrientGoalEvent.OnCarbsRatioEnter(it)) },
                    unit = stringResource(id = R.string.percent_carbs),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    focusRequester = carbsFocus
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    initValue = "30",
                    onValueChange = { onEvent(NutrientGoalEvent.OnProteinRatioEnter(it)) },
                    unit = stringResource(id = R.string.percent_proteins),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    focusRequester = proteinsFocus
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    initValue = "30",
                    onValueChange = { onEvent(NutrientGoalEvent.OnFatRatioEnter(it)) },
                    unit = stringResource(id = R.string.percent_fats),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    focusRequester = fatsFocus
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(NutrientGoalEvent.OnNextClick) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@DevicePreviews
@Composable
fun NutrientGoalScreenPrev() {
    NutrientGoalScreen(modifier = Modifier.background(Color.White))
}

