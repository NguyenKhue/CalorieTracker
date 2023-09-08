package com.khue.calorietracker.onboarding.onboarding_presentation.height

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface HeightNavigationEvent: NavigateEvent {
    data object NavigateToWeightScreen: HeightNavigationEvent
}