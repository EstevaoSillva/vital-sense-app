package com.mindsense.feature.sync

import com.mindsense.domain.model.WatchSyncStatus

data class SyncUiState(
    val status: WatchSyncStatus? = null,
    val infoMessage: String? = null,
)

sealed interface SyncAction {
    data object Load : SyncAction
    data object SyncNow : SyncAction
    data object TestConnection : SyncAction
    data object OpenSettings : SyncAction
}
