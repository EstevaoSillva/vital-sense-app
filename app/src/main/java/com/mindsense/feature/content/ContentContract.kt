package com.mindsense.feature.content

import com.mindsense.domain.model.ArticleSummary

data class ContentUiState(
    val allArticles: List<ArticleSummary> = emptyList(),
    val articles: List<ArticleSummary> = emptyList(),
    val query: String = "",
    val selectedCategory: String = "Todos",
)

sealed interface ContentAction {
    data object Load : ContentAction
    data class QueryChanged(val value: String) : ContentAction
    data class CategorySelected(val value: String) : ContentAction
}
