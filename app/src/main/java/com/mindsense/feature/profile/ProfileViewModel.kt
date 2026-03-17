package com.mindsense.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.AuthRepository
import com.mindsense.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        onAction(ProfileAction.Load)
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.Load -> {
                viewModelScope.launch {
                    val profile = profileRepository.getProfile()
                    _uiState.value = _uiState.value.copy(profile = profile)
                }
            }
            ProfileAction.Logout -> {
                viewModelScope.launch {
                    authRepository.logout()
                }
            }
        }
    }
}
