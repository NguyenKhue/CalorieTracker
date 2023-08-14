package com.khue.calorietracker.navigation

import androidx.navigation.NavController
import com.khue.calorietracker.core.util.UiEvent


fun NavController.navigate(event: UiEvent.Navigate) {
    navigate(event.route)
}