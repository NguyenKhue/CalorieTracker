package com.khue.calorietracker.onboarding.onboarding_presentation.goal

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface GoalNavigationEvent: NavigationEvent {
    data object NavigateToNutrientGoalScreen: GoalNavigationEvent
}