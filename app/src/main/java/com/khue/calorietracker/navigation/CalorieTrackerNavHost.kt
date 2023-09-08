package com.khue.calorietracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
import com.khue.calorietracker.onboarding.onboarding_presentation.navigation.ONBOARDING_GRAPH_ROUTE_PATTERN
import com.khue.calorietracker.onboarding.onboarding_presentation.navigation.onboardingGraph
import com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.navigation.navigateToNutrientGoal
import com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal.navigation.nutrientGoalScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.navigateToWeight
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.weightScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeScreen
import com.khue.calorietracker.tracker.tracker_presentation.navigation.trackerGraph
import com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.navigation.navigateToTrackerOverview

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

        onboardingGraph {
            welcomeScreen(onNavigateToGenderScreen = navController::navigateToGender)

            genderScreen(onNavigateToAgeScreen = navController::navigateToAge)

            ageScreen(onNavigateToHeightScreen = navController::navigateToHeight, onShowSnackbar = onShowSnackbar)

            heightScreen(onNavigateToWeightScreen = navController::navigateToWeight, onShowSnackbar = onShowSnackbar)

            weightScreen(onNavigateToActivityLevelScreen = navController::navigateToActivityLevel, onShowSnackbar = onShowSnackbar)

            activityLevelScreen(onNavigateToGoalScreen = navController::navigateToGoal)

            goalScreen(onNavigateToNutrientGoalScreen = navController::navigateToNutrientGoal)

            nutrientGoalScreen(onNavigateToTrackerOverviewScreen = navController::navigateToTrackerOverview, onShowSnackbar = onShowSnackbar)
        }

        trackerGraph(navController = navController, onShowSnackbar = onShowSnackbar)
    }
}