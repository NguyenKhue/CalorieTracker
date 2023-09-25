package com.khue.calorietracker.onboarding.onboarding_presentation.height.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.height.HeightRoute

const val heightRoute = "height_route"

fun NavController.navigateToHeight(navOptions: NavOptions? = null) {
    this.navigate(heightRoute, navOptions)
}

fun NavGraphBuilder.heightScreen(
    onNavigateToWeightScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = heightRoute) {
        HeightRoute(onNavigateToWeightScreen = onNavigateToWeightScreen, onShowSnackbar = onShowSnackbar)
    }
}