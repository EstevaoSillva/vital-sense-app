package com.mindsense.feature.profile

data class ProfileUiState(
    val title: String = "Perfil",
    val description: String = "Dados do usuário, preferências e logout já têm ponto de extensão definido.",
)

sealed interface ProfileAction {
    data object Load : ProfileAction
    data object Logout : ProfileAction
}
