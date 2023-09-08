package com.khue.calorietracker.onboarding.onboarding_presentation.gender

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface GenderNavigationEvent: NavigateEvent {
    data object NavigateToAgeScreen: GenderNavigationEvent
}