package com.mindsense.feature.notifications

data class NotificationsUiState(
    val title: String = "Notificações",
    val description: String = "Categorias e feed já têm fonte fake e estrutura pronta para filtros reais.",
)

sealed interface NotificationsAction {
    data object Load : NotificationsAction
}
