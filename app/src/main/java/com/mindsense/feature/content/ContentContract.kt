package com.mindsense.feature.content

import com.mindsense.domain.model.ArticleSummary

data class ContentUiState(
    val articles: List<ArticleSummary> = emptyList(),
)

sealed interface ContentAction {
    data object Load : ContentAction
}
