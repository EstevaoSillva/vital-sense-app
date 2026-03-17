package com.mindsense.feature.profile

import com.mindsense.domain.model.UserProfile

data class ProfileUiState(
    val profile: UserProfile? = null,
)

sealed interface ProfileAction {
    data object Load : ProfileAction
    data object Logout : ProfileAction
}
