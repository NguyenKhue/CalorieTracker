package com.khue.calorietracker.tracker.tracker_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.navigation.trackerOverviewRoute

private const val TRACKER_GRAPH_ROUTE_PATTERN = "tracker_graph"

fun NavGraphBuilder.trackerGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = TRACKER_GRAPH_ROUTE_PATTERN,
        startDestination = trackerOverviewRoute,
    ) {

        nestedGraphs()
    }
}