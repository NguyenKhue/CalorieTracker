package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.NutrientGoalRoute

const val nutrientGoalRoute = "nutrient_goal_route"

fun NavController.navigateToNutrientGoal(navOptions: NavOptions? = null) {
    this.navigate(nutrientGoalRoute, navOptions)
}

fun NavGraphBuilder.nutrientGoalScreen(
    onNavigateToTrackerOverviewScreen: (NavOptions?) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = nutrientGoalRoute) {
        NutrientGoalRoute(onNavigateToTrackerOverviewScreen = onNavigateToTrackerOverviewScreen, onShowSnackbar = onShowSnackbar)
    }
}