package com.mindsense.feature.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.model.ArticleDetail
import com.mindsense.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contentRepository: ContentRepository,
) : ViewModel() {
    private val articleId: String = savedStateHandle["articleId"] ?: "1"
    private val _article = MutableStateFlow<ArticleDetail?>(null)
    val article: StateFlow<ArticleDetail?> = _article.asStateFlow()

    init {
        viewModelScope.launch {
            _article.value = contentRepository.getArticleDetail(articleId)
        }
    }
}
