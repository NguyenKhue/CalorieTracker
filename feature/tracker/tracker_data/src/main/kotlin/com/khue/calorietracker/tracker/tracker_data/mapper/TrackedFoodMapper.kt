package com.khue.calorietracker.tracker.tracker_data.mapper

import com.khue.calorietracker.tracker.tracker_data.local.entity.TrackedFoodEntity
import com.khue.calorietracker.tracker.tracker_domain.model.MealType
import com.khue.calorietracker.tracker.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood() = TrackedFood(
    name = name,
    imageUrl = imageUrl,
    carbs = carbs,
    calories = calories,
    fat = fat,
    protein = protein,
    mealType = MealType.fromString(type),
    amount = amount,
    date = LocalDate.of(year, month, dayOfMonth),
    id = id
)

fun TrackedFood.toTrackedFoodEntity() = TrackedFoodEntity(
    name = name,
    imageUrl = imageUrl,
    carbs = carbs,
    calories = calories,
    fat = fat,
    protein = protein,
    type = mealType.name,
    amount = amount,
    dayOfMonth = date.dayOfMonth,
    month = date.monthValue,
    year = date.year,
    id = id
)