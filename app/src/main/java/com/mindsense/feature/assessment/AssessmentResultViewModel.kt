package com.mindsense.feature.assessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.model.AssessmentResult
import com.mindsense.domain.repository.AssessmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AssessmentResultViewModel @Inject constructor(
    private val assessmentRepository: AssessmentRepository,
) : ViewModel() {
    private val _result = MutableStateFlow<AssessmentResult?>(null)
    val result: StateFlow<AssessmentResult?> = _result.asStateFlow()

    init {
        viewModelScope.launch {
            _result.value = assessmentRepository.submitAnswers(emptyMap())
        }
    }
}
