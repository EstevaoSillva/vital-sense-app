package com.mindsense.feature.history

import com.mindsense.domain.model.CollectionSession

data class HistoryUiState(
    val collections: List<CollectionSession> = emptyList(),
)

sealed interface HistoryAction {
    data object Load : HistoryAction
}
