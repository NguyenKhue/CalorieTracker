package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview

import com.khue.calorietracker.core.common.util.NavigationEvent
import java.time.LocalDate

sealed class TrackerOverviewNavigationEvent: NavigationEvent {
    data class NavigateToSearch(val mealName: String, val date: LocalDate): TrackerOverviewNavigationEvent()
}