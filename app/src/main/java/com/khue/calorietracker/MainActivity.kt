package com.khue.calorietracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khue.calorietracker.core.designsystem.ui.theme.CalorieTrackerTheme
import com.khue.calorietracker.core.ui.navigation.Route
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
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.navigateToWeight
import com.khue.calorietracker.onboarding.onboarding_presentation.weight.navigation.weightScreen
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeRoute
import com.khue.calorietracker.onboarding.onboarding_presentation.welcome.navigation.welcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackbarHostState = remember { SnackbarHostState() }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = welcomeRoute
                        ) {
                            welcomeScreen(onNavigateToGenderScreen = navController::navigateToGender)

                            genderScreen(onNavigateToAgeScreen = navController::navigateToAge)

                            ageScreen(onNavigateToHeightScreen = navController::navigateToHeight) { message, action ->
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    actionLabel = action,
                                    duration = SnackbarDuration.Short,
                                ) == SnackbarResult.ActionPerformed
                            }

                            heightScreen(onNavigateToWeightScreen = navController::navigateToWeight) { message, action ->
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    actionLabel = action,
                                    duration = SnackbarDuration.Short,
                                ) == SnackbarResult.ActionPerformed
                            }

                            weightScreen(onNavigateToActivityLevelScreen = navController::navigateToActivityLevel) { message, action ->
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    actionLabel = action,
                                    duration = SnackbarDuration.Short,
                                ) == SnackbarResult.ActionPerformed
                            }

                            activityLevelScreen(onNavigateToGoalScreen = navController::navigateToGoal)

                            goalScreen(onNavigateToAgeScreen = navController::navigateToAge)

                            composable(Route.NUTRIENT_GOAL) {

                            }

                            composable(Route.TRACKER_OVERVIEW) {

                            }
                            composable(Route.SEARCH) {

                            }
                        }
                    }


                }
            }
        }
    }
}
