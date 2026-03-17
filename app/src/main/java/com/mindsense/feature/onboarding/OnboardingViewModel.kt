package com.mindsense.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    companion object {
        const val TOTAL_SLIDES = 3
    }

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.ContinueClicked -> {
                val next = _uiState.value.currentIndex + 1
                if (next < TOTAL_SLIDES) {
                    _uiState.value = _uiState.value.copy(currentIndex = next)
                } else {
                    markCompleted()
                }
            }
            OnboardingAction.SkipClicked -> {
                markCompleted()
            }
        }
    }

    private fun markCompleted() {
        viewModelScope.launch {
            authRepository.setOnboardingCompleted(true)
        }
    }
}
