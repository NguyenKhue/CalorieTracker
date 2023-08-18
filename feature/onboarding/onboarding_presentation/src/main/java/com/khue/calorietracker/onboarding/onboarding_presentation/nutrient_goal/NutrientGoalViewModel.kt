package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.domain.use_case.FilterOutDigits
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filerOutDigits: FilterOutDigits
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbsRatioEnter -> onCarbsRatioEnter(event.carbsRatio)
            is NutrientGoalEvent.OnProteinRatioEnter -> onProteinRatioEnter(event.proteinRatio)
            is NutrientGoalEvent.OnFatRatioEnter -> onFatRatioEnter(event.fatRatio)
            is NutrientGoalEvent.OnNextClick -> onNextClick()
        }
    }

    private fun onCarbsRatioEnter(carbsRatio: String) {
        state = state.copy(carbsRatio = filerOutDigits(carbsRatio))
    }

    private fun onProteinRatioEnter(proteinRatio: String) {
        state = state.copy(proteinRatio = filerOutDigits(proteinRatio))
    }

    private fun onFatRatioEnter(fatRatio: String) {
        state = state.copy(fatRatio = filerOutDigits(fatRatio))
    }

    fun onNextClick() {
        preferences.saveCarbRatio(state.carbsRatio.toFloat())
        preferences.saveProteinRatio(state.proteinRatio.toFloat())
        preferences.saveFatRatio(state.fatRatio.toFloat())
    }

}