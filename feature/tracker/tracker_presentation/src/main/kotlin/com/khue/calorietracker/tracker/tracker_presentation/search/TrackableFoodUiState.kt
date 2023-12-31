package com.khue.calorietracker.tracker.tracker_presentation.search

import com.khue.calorietracker.tracker.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
