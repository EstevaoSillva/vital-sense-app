package com.mindsense.feature.onboarding

data class OnboardingUiState(
    val title: String = "Onboarding",
    val description: String = "Base da feature pronta. As telas reais entram na Fase 5 sobre esta infraestrutura.",
)

sealed interface OnboardingAction {
    data object ContinueClicked : OnboardingAction
}
