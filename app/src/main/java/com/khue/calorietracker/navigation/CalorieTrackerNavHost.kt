package com.khue.calorietracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khue.calorietracker.core.ui.navigation.Route
import com.khue.calorietracker.onboarding.onboarding_presentation.navigation.ONBOARDING_GRAPH_ROUTE_PATTERN
import com.khue.calorietracker.onboarding.onboarding_presentation.navigation.onboardingGraph

@Composable
fun CalorieTrackerNavHost(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = ONBOARDING_GRAPH_ROUTE_PATTERN,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        onboardingGraph(navController = navController, onShowSnackbar = onShowSnackbar)

        composable(Route.TRACKER_OVERVIEW) {

        }
        composable(Route.SEARCH) {

        }
    }
}