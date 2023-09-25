package com.khue.calorietracker.core.common.util

sealed class UiEvent {
    data class Navigate(val navigationEvent: NavigationEvent) : UiEvent()
    data object NavigateUp : UiEvent()

    data class ShowSnackbar(val message: UiText): UiEvent()
}


interface NavigationEvent