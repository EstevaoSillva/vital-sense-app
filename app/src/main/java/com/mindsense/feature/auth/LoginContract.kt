package com.mindsense.feature.auth

data class LoginUiState(
    val title: String = "Login",
    val description: String = "Autenticação fake ligada a DataStore. Troca real com backend entra em etapa posterior.",
)

sealed interface LoginAction {
    data class Submit(val email: String, val password: String) : LoginAction
}
