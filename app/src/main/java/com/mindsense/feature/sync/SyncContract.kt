package com.mindsense.feature.sync

import com.mindsense.domain.model.WatchSyncStatus

data class SyncUiState(
    val status: WatchSyncStatus? = null,
)

sealed interface SyncAction {
    data object Load : SyncAction
    data object SyncNow : SyncAction
}
