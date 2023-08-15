package com.khue.calorietracker.core.preferences.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val high: Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
