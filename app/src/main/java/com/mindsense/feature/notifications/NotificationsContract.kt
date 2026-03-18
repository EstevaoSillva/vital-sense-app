package com.mindsense.feature.notifications

import com.mindsense.domain.model.NotificationItem

data class NotificationsUiState(
    val allNotifications: List<NotificationItem> = emptyList(),
    val notifications: List<NotificationItem> = emptyList(),
    val selectedFilter: String = "Todos",
)

sealed interface NotificationsAction {
    data object Load : NotificationsAction
    data class FilterChanged(val value: String) : NotificationsAction
}
