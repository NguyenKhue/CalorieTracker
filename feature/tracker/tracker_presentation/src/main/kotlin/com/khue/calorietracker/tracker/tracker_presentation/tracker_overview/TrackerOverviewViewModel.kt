package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import com.khue.calorietracker.tracker.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel  @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    init {
        preferences.saveShouldShowOnboarding(false)
    }



    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnNextDayClick -> onNextDayClick()
            is TrackerOverviewEvent.OnPreviousDayClick -> onPreviousDayClick()
            is TrackerOverviewEvent.OnToggleMealClick -> onToggleMealClick(event.meal)
            is TrackerOverviewEvent.OnDeleteTrackedFood -> onDeleteTrackedFood(event.trackedFood)
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                }
            }
        }
    }
}