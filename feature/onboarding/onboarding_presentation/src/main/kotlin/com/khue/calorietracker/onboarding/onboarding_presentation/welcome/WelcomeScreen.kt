package com.khue.calorietracker.onboarding.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavOptions
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing
import com.khue.calorietracker.core.ui.DevicePreviews
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.onboarding.onboarding_presentation.components.ActionButton


@Composable
internal fun WelcomeRoute(
    onNavigateToGenderScreen: (NavOptions?) -> Unit,
    modifier: Modifier = Modifier,
) {

    WelcomeScreen(
        onNavigateToGenderScreen = onNavigateToGenderScreen,
        modifier = modifier
    )
}

@Composable
fun WelcomeScreen(
    onNavigateToGenderScreen: (NavOptions?) -> Unit,
    modifier: Modifier
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onNavigateToGenderScreen(null) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@DevicePreviews
@Composable
fun WelcomeScreenPrev() {
    WelcomeScreen({}, Modifier)
}