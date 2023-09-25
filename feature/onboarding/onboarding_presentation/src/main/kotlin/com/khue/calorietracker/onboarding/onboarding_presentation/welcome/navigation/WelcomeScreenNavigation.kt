package com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.WelcomeRoute

const val welcomeRoute = "welcome_route"
fun NavGraphBuilder.welcomeScreen(
    onNavigateToGenderScreen: (NavOptions?) -> Unit
) {
    composable(route = welcomeRoute) {
        WelcomeRoute(onNavigateToGenderScreen = onNavigateToGenderScreen)
    }
}