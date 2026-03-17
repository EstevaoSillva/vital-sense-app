package com.mindsense.feature.assessment

data class AssessmentUiState(
    val title: String = "Assessment",
    val description: String = "Questionário e resultado consolidado já têm modelos e contrato de submissão fake.",
)

sealed interface AssessmentAction {
    data object Load : AssessmentAction
    data object Submit : AssessmentAction
}
