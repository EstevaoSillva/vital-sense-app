package com.mindsense.feature.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.core.common.result.AppResult
import com.mindsense.domain.repository.SyncRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val syncRepository: SyncRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SyncUiState())
    val uiState: StateFlow<SyncUiState> = _uiState.asStateFlow()

    init {
        onAction(SyncAction.Load)
    }

    fun onAction(action: SyncAction) {
        when (action) {
            SyncAction.Load -> {
                viewModelScope.launch {
                    val status = syncRepository.getSyncStatus()
                    _uiState.value = _uiState.value.copy(status = status)
                }
            }
            SyncAction.SyncNow -> {
                viewModelScope.launch {
                    when (val result = syncRepository.syncNow()) {
                        is AppResult.Success -> {
                            _uiState.value = _uiState.value.copy(status = result.data)
                        }
                        is AppResult.Error -> Unit
                    }
                }
            }
        }
    }
}
