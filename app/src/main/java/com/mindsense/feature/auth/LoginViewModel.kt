package com.mindsense.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> _uiState.value = _uiState.value.copy(email = action.value)
            is LoginAction.PasswordChanged -> _uiState.value = _uiState.value.copy(password = action.value)
            LoginAction.TogglePasswordVisibility -> _uiState.value = _uiState.value.copy(
                showPassword = !_uiState.value.showPassword,
            )
            is LoginAction.Submit -> {
                viewModelScope.launch {
                    authRepository.login(action.email, action.password)
                }
            }
        }
    }
}
