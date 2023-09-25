package com.khue.calorietracker.onboarding.onboarding_presentation.height

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface HeightNavigationEvent: NavigationEvent {
    data object NavigateToWeightScreen: HeightNavigationEvent
}