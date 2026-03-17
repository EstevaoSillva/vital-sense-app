package com.mindsense.feature.dashboard

data class DashboardUiState(
    val title: String = "Dashboard",
    val description: String = "Resumo inicial conectado ao repositório fake e pronto para receber os cards reais.",
)

sealed interface DashboardAction {
    data object Load : DashboardAction
}
