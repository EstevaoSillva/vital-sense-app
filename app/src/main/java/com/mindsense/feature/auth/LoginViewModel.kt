package com.mindsense.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.core.common.result.AppResult
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
            is LoginAction.EmailChanged -> _uiState.value = _uiState.value.copy(
                email = action.value,
                emailError = null,
                submitError = null,
            )
            is LoginAction.PasswordChanged -> _uiState.value = _uiState.value.copy(
                password = action.value,
                passwordError = null,
                submitError = null,
            )
            LoginAction.TogglePasswordVisibility -> _uiState.value = _uiState.value.copy(
                showPassword = !_uiState.value.showPassword,
            )
            is LoginAction.Submit -> {
                val emailError = validateEmail(action.email)
                val passwordError = validatePassword(action.password)
                if (emailError != null || passwordError != null) {
                    _uiState.value = _uiState.value.copy(
                        emailError = emailError,
                        passwordError = passwordError,
                        submitError = null,
                    )
                    return
                }

                viewModelScope.launch {
                    _uiState.value = _uiState.value.copy(isSubmitting = true, submitError = null)
                    when (val result = authRepository.login(action.email, action.password)) {
                        is AppResult.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isSubmitting = false,
                                isAuthenticated = true,
                            )
                        }
                        is AppResult.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isSubmitting = false,
                                submitError = result.message,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String): String? {
        if (email.isBlank()) return "Informe seu e-mail."
        if (!email.contains("@")) return "Digite um e-mail válido."
        return null
    }

    private fun validatePassword(password: String): String? {
        if (password.isBlank()) return "Informe sua senha."
        if (password.length < 6) return "A senha deve ter pelo menos 6 caracteres."
        return null
    }
}
