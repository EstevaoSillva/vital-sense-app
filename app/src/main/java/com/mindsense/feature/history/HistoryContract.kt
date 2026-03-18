package com.mindsense.feature.history

import com.mindsense.domain.model.CollectionSession

data class HistoryUiState(
    val allCollections: List<CollectionSession> = emptyList(),
    val collections: List<CollectionSession> = emptyList(),
    val selectedFilter: String = "Todos",
)

sealed interface HistoryAction {
    data object Load : HistoryAction
    data class FilterChanged(val value: String) : HistoryAction
}
