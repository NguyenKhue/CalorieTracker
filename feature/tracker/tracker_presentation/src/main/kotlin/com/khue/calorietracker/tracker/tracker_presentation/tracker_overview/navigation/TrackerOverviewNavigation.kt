package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.TrackerOverviewRoute

const val trackerOverviewRoute = "tracker_overview_route"

fun NavController.navigateToTrackerOverview(navOptions: NavOptions? = null) {
    this.navigate(trackerOverviewRoute, navOptions)
}

fun NavGraphBuilder.trackerOverviewScreen(
    onNavigateToSearchScreen: (String, Int, Int, Int, NavOptions?) -> Unit
) {
    composable(route = trackerOverviewRoute) {
        TrackerOverviewRoute(onNavigateToSearchScreen = onNavigateToSearchScreen)
    }
}