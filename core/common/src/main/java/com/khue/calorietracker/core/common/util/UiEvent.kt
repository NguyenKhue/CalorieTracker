package com.khue.calorietracker.core.common.util

sealed class UiEvent {
    data class Navigate(val navigateEvent: NavigateEvent) : UiEvent()
    data object NavigateUp : UiEvent()

    data class ShowSnackbar(val message: UiText): UiEvent()
}


interface NavigateEvent