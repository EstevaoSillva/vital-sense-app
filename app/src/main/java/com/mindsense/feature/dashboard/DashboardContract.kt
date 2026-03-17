package com.mindsense.feature.dashboard

import com.mindsense.domain.model.StressSnapshot

data class DashboardUiState(
    val snapshot: StressSnapshot? = null,
)

sealed interface DashboardAction {
    data object Load : DashboardAction
}
