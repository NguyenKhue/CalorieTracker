package com.khue.calorietracker.onboarding.onboarding_presentation.age

import com.khue.calorietracker.core.common.util.NavigateEvent

sealed interface AgeNavigationEvent: NavigateEvent {
    data object NavigateToHeightScreen: AgeNavigationEvent
}