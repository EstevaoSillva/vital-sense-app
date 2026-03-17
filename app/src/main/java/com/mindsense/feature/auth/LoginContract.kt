package com.mindsense.feature.auth

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
)

sealed interface LoginAction {
    data class EmailChanged(val value: String) : LoginAction
    data class PasswordChanged(val value: String) : LoginAction
    data object TogglePasswordVisibility : LoginAction
    data class Submit(val email: String, val password: String) : LoginAction
}
