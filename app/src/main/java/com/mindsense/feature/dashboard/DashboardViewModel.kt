package com.mindsense.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        onAction(DashboardAction.Load)
    }

    fun onAction(action: DashboardAction) {
        if (action is DashboardAction.Load) {
            viewModelScope.launch {
                val snapshot = dashboardRepository.getDashboardSnapshot()
                _uiState.value = _uiState.value.copy(snapshot = snapshot)
            }
        }
    }
}
