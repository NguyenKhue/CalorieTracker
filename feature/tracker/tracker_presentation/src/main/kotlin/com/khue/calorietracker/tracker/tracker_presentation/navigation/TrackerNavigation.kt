package com.khue.calorietracker.tracker.tracker_presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.khue.calorietracker.core.ui.navigation.Route
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.navigation.trackerOverviewRoute
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.navigation.trackerOverviewScreen

private const val TRACKER_GRAPH_ROUTE_PATTERN = "tracker_graph"

fun NavGraphBuilder.trackerGraph(
    navController: NavController,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    navigation(
        route = TRACKER_GRAPH_ROUTE_PATTERN,
        startDestination = trackerOverviewRoute,
    ) {

        trackerOverviewScreen()

        composable(Route.SEARCH) {

        }
    }
}