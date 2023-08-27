package com.khue.calorietracker.tracker.tracker_domain.use_case

import com.khue.calorietracker.tracker.tracker_domain.model.TrackedFood
import com.khue.calorietracker.tracker.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        food: TrackedFood
    ) = repository.deleteTrackedFood(food)
}