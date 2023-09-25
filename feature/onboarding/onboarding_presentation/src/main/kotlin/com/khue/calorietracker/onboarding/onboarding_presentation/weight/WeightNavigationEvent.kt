package com.khue.calorietracker.onboarding.onboarding_presentation.weight

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface WeightNavigationEvent: NavigationEvent {
    data object NavigateToActivityLevelScreen : WeightNavigationEvent
}