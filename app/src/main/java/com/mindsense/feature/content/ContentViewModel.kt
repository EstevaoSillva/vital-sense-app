package com.mindsense.feature.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContentUiState())
    val uiState: StateFlow<ContentUiState> = _uiState.asStateFlow()

    init {
        onAction(ContentAction.Load)
    }

    fun onAction(action: ContentAction) {
        when (action) {
            ContentAction.Load -> {
                viewModelScope.launch {
                    val articles = contentRepository.getFeaturedArticles()
                    _uiState.value = _uiState.value.copy(
                        allArticles = articles,
                        articles = articles,
                    )
                }
            }
            is ContentAction.QueryChanged -> {
                _uiState.value = _uiState.value.copy(query = action.value)
                applyFilters()
            }
            is ContentAction.CategorySelected -> {
                _uiState.value = _uiState.value.copy(selectedCategory = action.value)
                applyFilters()
            }
        }
    }

    private fun applyFilters() {
        val state = _uiState.value
        val normalizedQuery = state.query.trim()
        val filtered = state.allArticles.filter { article ->
            val matchesQuery = normalizedQuery.isBlank() ||
                article.title.contains(normalizedQuery, ignoreCase = true) ||
                article.category.contains(normalizedQuery, ignoreCase = true)
            val matchesCategory = state.selectedCategory == "Todos" ||
                article.category.equals(state.selectedCategory, ignoreCase = true)
            matchesQuery && matchesCategory
        }
        _uiState.value = state.copy(articles = filtered)
    }
}
