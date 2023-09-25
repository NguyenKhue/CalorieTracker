package com.khue.calorietracker.onboarding.onboarding_presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.core.preferences.domain.model.GoalType
import com.khue.calorietracker.onboarding.onboarding_presentation.components.ActionButton
import com.khue.calorietracker.onboarding.onboarding_presentation.components.SelectableButton

@Composable
internal fun GoalRoute(
    onNavigateToNutrientGoalScreen: (NavOptions?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GoalViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    when(event.navigationEvent) {
                        is GoalNavigationEvent.NavigateToNutrientGoalScreen -> onNavigateToNutrientGoalScreen(null)
                    }
                }
                else -> Unit
            }
        }
    }

    GoalScreen(
        modifier = modifier,
        selectedGoalType = viewModel.selectedGoalType,
        onGoalTypeClick = viewModel::onGoalTypeClick,
        onNextClick = viewModel::onNextClick,
    )
}

@Composable
fun GoalScreen(
    modifier: Modifier = Modifier,
    selectedGoalType: GoalType,
    onGoalTypeClick: (GoalType) -> Unit,
    onNextClick: () -> Unit,
) {

    val spacing = LocalSpacing.current

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
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = selectedGoalType is GoalType.LoseWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = { onGoalTypeClick(GoalType.LoseWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = selectedGoalType is GoalType.KeepWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = { onGoalTypeClick(GoalType.KeepWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = selectedGoalType is GoalType.GainWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = { onGoalTypeClick(GoalType.GainWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}