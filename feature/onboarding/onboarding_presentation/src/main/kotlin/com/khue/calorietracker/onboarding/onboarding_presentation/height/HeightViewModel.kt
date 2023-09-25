package com.khue.calorietracker.onboarding.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khue.calorietracker.core.common.R
import com.khue.calorietracker.core.common.util.UiEvent
import com.khue.calorietracker.core.common.util.UiText
import com.khue.calorietracker.core.domain.use_case.FilterOutDigits
import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {
    var height by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height: String) {
       this.height = height
    }

    fun heightFilter(height: String): String {
        val newHeight = if (height.length > 3) {
            height.subSequence(0, 3).toString()
        } else {
            height
        }
        return filterOutDigits(newHeight)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_height_cant_be_empty)))
                return@launch
            }
            preferences.saveHigh(heightNumber)
            _uiEvent.send(UiEvent.Navigate(HeightNavigationEvent.NavigateToWeightScreen))
        }
    }
}