package com.khue.calorietracker.onboarding.onboarding_presentation.weight

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface WeightNavigationEvent: NavigateEvent {
    data object NavigateToActivityLevelScreen : WeightNavigationEvent
}