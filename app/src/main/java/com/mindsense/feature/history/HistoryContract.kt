package com.mindsense.feature.history

data class HistoryUiState(
    val title: String = "Histórico",
    val description: String = "Lista de coletas fake pronta para virar `LazyColumn` com filtros na Fase 5.",
)

sealed interface HistoryAction {
    data object Load : HistoryAction
}
