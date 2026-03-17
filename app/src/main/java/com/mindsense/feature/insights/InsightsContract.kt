package com.mindsense.feature.insights

import com.mindsense.domain.model.InsightSummary
import com.mindsense.domain.model.Recommendation

data class InsightsUiState(
    val summary: InsightSummary? = null,
    val recommendations: List<Recommendation> = emptyList(),
)

sealed interface InsightsAction {
    data object Load : InsightsAction
}
