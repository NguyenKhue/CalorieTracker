package com.khue.calorietracker.core.domain.use_case

import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiText

class ValidateNutrients {
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String,
    ): ValidateNutrientsResult {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if (carbsRatio == null || proteinRatio == null || fatRatio == null) {
            return ValidateNutrientsResult.Error(
                UiText.StringResource(R.string.error_invalid_values)
            )
        }

        val total = carbsRatio + proteinRatio + fatRatio
        if (total != 100) {
            return ValidateNutrientsResult.Error(
                UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return ValidateNutrientsResult.Success(
            carbsRatio = carbsRatio / 100f,
            proteinRatio = proteinRatio / 100f,
            fatRatio = fatRatio / 100f,
        )
    }

    sealed class ValidateNutrientsResult {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float,
        ) : ValidateNutrientsResult()

        data class Error(val message: UiText): ValidateNutrientsResult()
    }
}