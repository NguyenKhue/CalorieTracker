package com.khue.calorietracker.tracker.tracker_domain.use_case

import com.khue.calorietracker.core.preferences.domain.model.ActivityLevel
import com.khue.calorietracker.core.preferences.domain.model.Gender
import com.khue.calorietracker.core.preferences.domain.model.GoalType
import com.khue.calorietracker.core.preferences.domain.model.UserInfo
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import com.khue.calorietracker.tracker.tracker_domain.model.MealType
import com.khue.calorietracker.tracker.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): MealNutrientsResult {
        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }

        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calorieGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (calorieGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (calorieGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (calorieGoal * userInfo.fatRatio / 9f).roundToInt()

        return MealNutrientsResult(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = calorieGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                (66.47f + (13.75f * userInfo.weight) + (5.003f * userInfo.height) - (6.755f * userInfo.age)).roundToInt()
            }

            is Gender.Female -> {
                (655.1f + (9.563f * userInfo.weight) + (1.85f * userInfo.height) - (4.676f * userInfo.age)).roundToInt()
            }
        }
    }

    private fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }

        val calorieExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.GainWeight -> 500
            is GoalType.KeepWeight -> 0
        }

        return ((bmr(userInfo) * activityFactor) + calorieExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class MealNutrientsResult(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )

}