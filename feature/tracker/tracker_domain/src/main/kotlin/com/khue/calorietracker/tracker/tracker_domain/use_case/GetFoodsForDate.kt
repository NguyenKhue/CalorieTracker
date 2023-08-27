package com.khue.calorietracker.tracker.tracker_domain.use_case

import com.khue.calorietracker.tracker.tracker_domain.model.TrackedFood
import com.khue.calorietracker.tracker.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> = repository.getFoodsForDate(date)
}