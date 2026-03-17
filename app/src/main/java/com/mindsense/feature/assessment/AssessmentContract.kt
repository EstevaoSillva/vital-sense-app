package com.mindsense.feature.assessment

import com.mindsense.domain.model.AssessmentQuestion

data class AssessmentUiState(
    val questions: List<AssessmentQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val answers: Map<String, Int> = emptyMap(),
)

sealed interface AssessmentAction {
    data object Load : AssessmentAction
    data class AnswerSelected(val questionId: String, val value: Int) : AssessmentAction
    data object Next : AssessmentAction
}
