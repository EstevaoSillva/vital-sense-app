package com.mindsense.feature.insights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.InsightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val insightsRepository: InsightsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InsightsUiState())
    val uiState: StateFlow<InsightsUiState> = _uiState.asStateFlow()

    init {
        onAction(InsightsAction.Load)
    }

    fun onAction(action: InsightsAction) {
        if (action is InsightsAction.Load) {
            viewModelScope.launch {
                val summary = insightsRepository.getInsightSummary()
                _uiState.value = _uiState.value.copy(
                    summary = summary,
                    recommendations = insightsRepository.getRecommendations(),
                )
            }
        }
    }
}
