package com.khue.calorietracker.tracker.tracker_domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodsForDate: GetFoodsForDate,
    val calculateMealNutrients: CalculateMealNutrients
)
