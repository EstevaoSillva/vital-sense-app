package com.mindsense.feature.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState: StateFlow<NotificationsUiState> = _uiState.asStateFlow()

    init {
        onAction(NotificationsAction.Load)
    }

    fun onAction(action: NotificationsAction) {
        when (action) {
            NotificationsAction.Load -> {
                viewModelScope.launch {
                    val notifications = notificationRepository.getNotifications()
                    _uiState.value = _uiState.value.copy(
                        allNotifications = notifications,
                        notifications = notifications,
                    )
                }
            }
            is NotificationsAction.FilterChanged -> {
                val filtered = if (action.value == "Todos") {
                    _uiState.value.allNotifications
                } else {
                    _uiState.value.allNotifications.filter {
                        it.category.equals(action.value, ignoreCase = true)
                    }
                }
                _uiState.value = _uiState.value.copy(
                    selectedFilter = action.value,
                    notifications = filtered,
                )
            }
        }
    }
}
