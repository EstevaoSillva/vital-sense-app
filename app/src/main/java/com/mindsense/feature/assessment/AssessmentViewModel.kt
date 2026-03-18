package com.mindsense.feature.assessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.AssessmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AssessmentViewModel @Inject constructor(
    private val assessmentRepository: AssessmentRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AssessmentUiState())
    val uiState: StateFlow<AssessmentUiState> = _uiState.asStateFlow()

    init {
        onAction(AssessmentAction.Load)
    }

    fun onAction(action: AssessmentAction) {
        when (action) {
            AssessmentAction.Load -> {
                viewModelScope.launch {
                    _uiState.value = _uiState.value.copy(questions = assessmentRepository.getQuestions())
                }
            }
            is AssessmentAction.AnswerSelected -> {
                _uiState.value = _uiState.value.copy(
                    answers = _uiState.value.answers + (action.questionId to action.value),
                    validationMessage = null,
                )
            }
            AssessmentAction.Next -> {
                val state = _uiState.value
                val question = state.questions.getOrNull(state.currentIndex) ?: return
                if (state.answers[question.id] == null) {
                    _uiState.value = state.copy(validationMessage = "Selecione uma resposta antes de continuar.")
                    return
                }
                if (state.currentIndex < state.questions.lastIndex) {
                    _uiState.value = state.copy(
                        currentIndex = state.currentIndex + 1,
                        validationMessage = null,
                    )
                }
            }
        }
    }
}
