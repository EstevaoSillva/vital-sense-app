package com.mindsense.feature.content

data class ContentUiState(
    val title: String = "Explorar",
    val description: String = "Descoberta de conteúdo, busca e leitura já têm contratos de domínio e navegação pronta.",
)

sealed interface ContentAction {
    data object Load : ContentAction
}
