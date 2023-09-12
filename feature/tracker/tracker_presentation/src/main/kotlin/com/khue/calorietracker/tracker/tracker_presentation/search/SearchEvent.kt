package com.khue.calorietracker.tracker.tracker_presentation.search

import com.khue.calorietracker.tracker.tracker_domain.model.MealType
import com.khue.calorietracker.tracker.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()

    data object OnSearch : SearchEvent()

    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()

    data class OnAmountForFoodChange(val food: TrackableFood, val amount: String) : SearchEvent()

    data class OnTrackFoodClick(
        val food: TrackableFood,
        val date: LocalDate,
        val mealType: MealType
    ) : SearchEvent()

    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
}