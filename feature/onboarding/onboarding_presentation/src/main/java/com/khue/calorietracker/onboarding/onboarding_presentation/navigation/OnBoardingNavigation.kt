package com.khue.calorietracker.onboarding.onboarding_presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.khue.calorietracker.onboarding.onboarding_presentation.activity.navigation.activityLevelScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.activity.navigation.navigateToActivityLevel
import com.khue.calorietracker.onboarding.onboarding_presentation.age.navigation.ageScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.age.navigation.navigateToAge
import com.khue.calorietracker.onboarding.onboarding_presentation.gender.navigation.genderScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.gender.navigation.navigateToGender
import com.khue.calorietracker.onboarding.onboarding_presentation.goal.navigation.goalScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.goal.navigation.navigateToGoal
import com.khue.calorietracker.onboarding.onboarding_presentation.height.navigation.heightScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.height.navigation.navigateToHeight
import com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.navigation.navigateToNutrientGoal
import com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.navigation.nutrientGoalScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.navigateToWeight
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.weightScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeRoute
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeScreen

const val ONBOARDING_GRAPH_ROUTE_PATTERN = "onboarding_graph"


fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    navigation(
        route = ONBOARDING_GRAPH_ROUTE_PATTERN,
        startDestination = welcomeRoute,
    ) {
        welcomeScreen(onNavigateToGenderScreen = navController::navigateToGender)

        genderScreen(onNavigateToAgeScreen = navController::navigateToAge)

        ageScreen(onNavigateToHeightScreen = navController::navigateToHeight, onShowSnackbar = onShowSnackbar)

        heightScreen(onNavigateToWeightScreen = navController::navigateToWeight, onShowSnackbar = onShowSnackbar)

        weightScreen(onNavigateToActivityLevelScreen = navController::navigateToActivityLevel, onShowSnackbar = onShowSnackbar)

        activityLevelScreen(onNavigateToGoalScreen = navController::navigateToGoal)

        goalScreen(onNavigateToNutrientGoalScreen = navController::navigateToNutrientGoal)

        nutrientGoalScreen(onNavigateToTrackerOverviewScreen = {_ ->}, onShowSnackbar = onShowSnackbar)
    }
}
