package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface NutrientGoalNavigationEvent: NavigationEvent {
    data object NavigateToTrackerOverView: NutrientGoalNavigationEvent
}