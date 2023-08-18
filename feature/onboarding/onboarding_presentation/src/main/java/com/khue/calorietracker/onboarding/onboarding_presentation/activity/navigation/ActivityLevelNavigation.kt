package com.khue.calorietracker.onboarding.onboarding_presentation.activity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.activity.ActivityLevelRoute

const val activityLevelRoute = "activity_level_route"

fun NavController.navigateToActivityLevel(navOptions: NavOptions? = null) {
    this.navigate(activityLevelRoute, navOptions)
}

fun NavGraphBuilder.activityLevelScreen(
    onNavigateToGoalScreen: (NavOptions?) -> Unit,
) {
    composable(route = activityLevelRoute) {
        ActivityLevelRoute(onNavigateToAgeScreen = onNavigateToGoalScreen)
    }
}