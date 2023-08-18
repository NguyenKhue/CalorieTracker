package com.khue.calorietracker.onboarding.onboarding_presentation.goal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.goal.GoalRoute

const val goalRoute = "goal_route"

fun NavController.navigateToGoal(navOptions: NavOptions? = null) {
    this.navigate(goalRoute, navOptions)
}

fun NavGraphBuilder.goalScreen(
    onNavigateToAgeScreen: (NavOptions?) -> Unit,
) {
    composable(route = goalRoute) {
        GoalRoute(onNavigateToAgeScreen = onNavigateToAgeScreen)
    }
}