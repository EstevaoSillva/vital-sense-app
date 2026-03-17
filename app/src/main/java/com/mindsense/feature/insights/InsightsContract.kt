package com.mindsense.feature.insights

data class InsightsUiState(
    val title: String = "Insights",
    val description: String = "Tendências, fatores críticos e recomendações já têm base de dados fake.",
)

sealed interface InsightsAction {
    data object Load : InsightsAction
}
