package com.khue.calorietracker.onboarding.onboarding_presentation.gender

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface GenderNavigationEvent: NavigationEvent {
    data object NavigateToAgeScreen: GenderNavigationEvent
}