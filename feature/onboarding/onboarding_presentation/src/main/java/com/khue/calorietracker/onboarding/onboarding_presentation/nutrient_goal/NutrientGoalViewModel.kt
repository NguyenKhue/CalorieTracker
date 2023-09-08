package com.khue.calorietracker.onboarding.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.domain.use_case.FilterOutDigits
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import com.khue.calorietracker.onboarding.onboarding_domain.use_cases.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filerOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients,
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
        state = state.copy(carbsRatio = carbsRatio)
    }

    private fun onProteinRatioEnter(proteinRatio: String) {
        state = state.copy(proteinRatio = proteinRatio)
    }

    private fun onFatRatioEnter(fatRatio: String) {
        state = state.copy(fatRatio = fatRatio)
    }

    fun nutrientTransform(ratio: String): String {
        val newRatio = if (ratio.length > 3) {
            ratio.subSequence(0, 3).toString()
        } else {
            ratio
        }
        return filerOutDigits(newRatio)
    }

    private fun onNextClick() {
        val nutrientsResult = validateNutrients(
            state.carbsRatio,
            state.proteinRatio,
            state.fatRatio,
        )

        when (nutrientsResult) {
            is ValidateNutrients.ValidateNutrientsResult.Success -> {
                preferences.saveCarbRatio(nutrientsResult.carbsRatio)
                preferences.saveProteinRatio(nutrientsResult.proteinRatio)
                preferences.saveFatRatio(nutrientsResult.fatRatio)

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(navigateEvent = NutrientGoalNavigationEvent.NavigateToTrackerOverView))
                }
            }
            is ValidateNutrients.ValidateNutrientsResult.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(nutrientsResult.message))
                }
            }
        }
    }

}