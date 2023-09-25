package com.khue.calorietracker.onboarding.onboarding_presentation.age.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.age.AgeRoute

const val ageRoute = "age_route"

fun NavController.navigateToAge(navOptions: NavOptions? = null) {
    this.navigate(ageRoute, navOptions)
}

fun NavGraphBuilder.ageScreen(
    onNavigateToHeightScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = ageRoute) {
        AgeRoute(onNavigateToHeightScreen = onNavigateToHeightScreen, onShowSnackbar = onShowSnackbar)
    }
}