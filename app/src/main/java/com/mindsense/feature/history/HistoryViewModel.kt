package com.mindsense.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        onAction(HistoryAction.Load)
    }

    fun onAction(action: HistoryAction) {
        if (action is HistoryAction.Load) {
            viewModelScope.launch {
                val count = historyRepository.getCollections().size
                _uiState.value = _uiState.value.copy(
                    description = "Bootstrap com $count coleta(s) fake disponível(eis) para navegação.",
                )
            }
        }
    }
}
