package com.khue.calorietracker.onboarding.onboarding_presentation.age

import com.khue.calorietracker.core.common.util.NavigationEvent

sealed interface AgeNavigationEvent: NavigationEvent {
    data object NavigateToHeightScreen: AgeNavigationEvent
}