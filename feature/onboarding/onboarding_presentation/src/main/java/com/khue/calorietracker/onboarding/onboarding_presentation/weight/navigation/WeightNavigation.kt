package com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.WeightRoute

const val weightRoute = "weight_route"

fun NavController.navigateToWeight(navOptions: NavOptions? = null) {
    this.navigate(weightRoute, navOptions)
}

fun NavGraphBuilder.weightScreen(
    onNavigateToActivityLevelScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = weightRoute) {
        WeightRoute(onNavigateToWeightScreen = onNavigateToActivityLevelScreen, onShowSnackbar = onShowSnackbar)
    }
}