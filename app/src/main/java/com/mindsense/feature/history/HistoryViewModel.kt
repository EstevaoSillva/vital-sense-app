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
        when (action) {
            HistoryAction.Load -> {
                viewModelScope.launch {
                    val collections = historyRepository.getCollections()
                    _uiState.value = _uiState.value.copy(
                        allCollections = collections,
                        collections = collections,
                    )
                }
            }
            is HistoryAction.FilterChanged -> {
                val source = _uiState.value.allCollections
                val filtered = when (action.value) {
                    "Hoje" -> source.filter { it.timestamp.contains("Hoje", ignoreCase = true) }
                    "7 dias" -> source.take(2)
                    "30 dias" -> source
                    else -> source
                }
                _uiState.value = _uiState.value.copy(
                    selectedFilter = action.value,
                    collections = filtered,
                )
            }
        }
    }
}
