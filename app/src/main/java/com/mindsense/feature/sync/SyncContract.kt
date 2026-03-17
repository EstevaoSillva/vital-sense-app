package com.mindsense.feature.sync

data class SyncUiState(
    val title: String = "Sincronização",
    val description: String = "Camada de sync pronta com Room, DataStore e repositório fake para evolução futura.",
)

sealed interface SyncAction {
    data object Load : SyncAction
    data object SyncNow : SyncAction
}
