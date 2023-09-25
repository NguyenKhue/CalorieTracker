package com.khue.calorietracker.onboarding.onboarding_presentation.gender.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.khue.calorietracker.onboarding.onboarding_presentation.gender.GenderRoute

const val genderRoute = "gender_route"

fun NavController.navigateToGender(navOptions: NavOptions? = null) {
    this.navigate(genderRoute, navOptions)
}

fun NavGraphBuilder.genderScreen(
    onNavigateToAgeScreen: (NavOptions?) -> Unit,
) {
    composable(route = genderRoute) {
        GenderRoute(onNavigateToAgeScreen = onNavigateToAgeScreen)
    }
}