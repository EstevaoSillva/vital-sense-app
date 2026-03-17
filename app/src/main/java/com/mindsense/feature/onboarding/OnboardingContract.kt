package com.mindsense.feature.onboarding

data class OnboardingUiState(
    val currentIndex: Int = 0,
)

sealed interface OnboardingAction {
    data object ContinueClicked : OnboardingAction
    data object SkipClicked : OnboardingAction
}
