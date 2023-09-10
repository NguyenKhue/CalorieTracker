package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview

import com.khue.calorietracker.tracker.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    data object OnNextDayClick : TrackerOverviewEvent()

    data object OnPreviousDayClick : TrackerOverviewEvent()

    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()

    data class OnDeleteTrackedFood(val trackedFood: TrackedFood) : TrackerOverviewEvent()

    data class OnAddFoodClick(val mealName: String) : TrackerOverviewEvent()
}