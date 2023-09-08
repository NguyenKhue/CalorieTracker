package com.khue.calorietracker.onboarding.onboarding_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeRoute

const val ONBOARDING_GRAPH_ROUTE_PATTERN = "onboarding_graph"


fun NavGraphBuilder.onboardingGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = ONBOARDING_GRAPH_ROUTE_PATTERN,
        startDestination = welcomeRoute,
    ) {
        nestedGraphs()
    }
}
