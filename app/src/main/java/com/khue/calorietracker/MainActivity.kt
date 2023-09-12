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
import com.khue.calorietracker.core.designsystem.ui.theme.CalorieTrackerTheme
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import com.khue.calorietracker.navigation.CalorieTrackerNavHost
import com.khue.calorietracker.onboarding.onboarding_presentation.navigation.ONBOARDING_GRAPH_ROUTE_PATTERN
import com.khue.calorietracker.tracker.tracker_presentation.navigation.TRACKER_GRAPH_ROUTE_PATTERN
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

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
                    val snackbarHostState = remember { SnackbarHostState() }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                    ) {
                        CalorieTrackerNavHost(
                            onShowSnackbar = { message, action ->
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    actionLabel = action,
                                    duration = SnackbarDuration.Short,
                                ) == SnackbarResult.ActionPerformed
                            },
                            startDestination = if (preferences.loadShouldShowOnboarding()) {
                                ONBOARDING_GRAPH_ROUTE_PATTERN
                            } else {
                                TRACKER_GRAPH_ROUTE_PATTERN
                            }
                        )
                    }
                }
            }
        }
    }
}
