package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface NutrientGoalNavigationEvent: NavigateEvent {
    data object NavigateToTrackerOverView: NutrientGoalNavigationEvent
}