package com.khue.calorietracker.onboarding.onboarding_presentation.height

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.core.ui.R
import com.khue.calorietracker.core.ui.util.UiEvent
import com.khue.calorietracker.onboarding.onboarding_presentation.components.ActionButton
import com.khue.calorietracker.onboarding.onboarding_presentation.components.UnitTextField

@Composable
internal fun HeightRoute(
    onNavigateToWeightScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: HeightViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigateToWeightScreen(null)
                is UiEvent.ShowSnackbar -> {
                    onShowSnackbar(event.message.asString(context), null)
                }
                else -> Unit
            }
        }
    }

    HeightScreen(
        onNextClick = viewModel::onNextClick,
        height = viewModel.height,
        onHeightEnter = viewModel::onHeightEnter,
        modifier = modifier
    )
}

@Composable
fun HeightScreen(
    onNextClick: () -> Unit,
    height: String,
    onHeightEnter: (String) -> Unit,
    modifier: Modifier = Modifier,
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
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = height,
                onValueChange = onHeightEnter,
                unit = stringResource(id = R.string.cm)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}