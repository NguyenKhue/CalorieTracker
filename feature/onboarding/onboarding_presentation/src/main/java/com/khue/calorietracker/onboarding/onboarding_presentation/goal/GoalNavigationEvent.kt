package com.khue.calorietracker.onboarding.onboarding_presentation.goal

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface GoalNavigationEvent: NavigateEvent {
    data object NavigateToNutrientGoalScreen: GoalNavigationEvent
}