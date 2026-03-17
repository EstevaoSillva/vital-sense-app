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
                )
            }
            AssessmentAction.Next -> {
                _uiState.value = _uiState.value.copy(currentIndex = _uiState.value.currentIndex + 1)
            }
        }
    }
}
