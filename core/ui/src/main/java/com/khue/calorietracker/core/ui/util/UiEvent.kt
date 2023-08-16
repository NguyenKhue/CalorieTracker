package com.khue.calorietracker.core.ui.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data object NavigateUp : UiEvent()

    data class ShowSnackbar(val message: UiText): UiEvent()
}