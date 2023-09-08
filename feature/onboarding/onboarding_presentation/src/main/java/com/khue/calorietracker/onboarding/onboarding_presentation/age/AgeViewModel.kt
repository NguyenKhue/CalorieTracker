package com.khue.calorietracker.onboarding.onboarding_presentation.age

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
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {
    var age by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String) {
        this.age = age
    }

    fun ageFilter(age: String): String {
        val newAge = if (age.length > 3) {
            age.subSequence(0, 3).toString()
        } else {
            age
        }
        return filterOutDigits(newAge)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_age_cant_be_empty)))
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Navigate(navigateEvent = AgeNavigationEvent.NavigateToHeightScreen))
        }
    }
}