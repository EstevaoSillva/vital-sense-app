package com.mindsense.feature.notifications

import com.mindsense.domain.model.NotificationItem

data class NotificationsUiState(
    val notifications: List<NotificationItem> = emptyList(),
)

sealed interface NotificationsAction {
    data object Load : NotificationsAction
}
