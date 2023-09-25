package com.khue.calorietracker.onboarding.onboarding_presentation.activity

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface ActivityLevelNavigationEvent: NavigationEvent {
    data object NavigateToGoalScreen: ActivityLevelNavigationEvent
}