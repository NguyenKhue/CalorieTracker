package com.khue.calorietracker.onboarding.onboarding_presentation.activity

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface ActivityLevelNavigationEvent: NavigateEvent {
    data object NavigateToGoalScreen: ActivityLevelNavigationEvent
}