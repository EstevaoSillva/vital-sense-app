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
        if (action is ContentAction.Load) {
            viewModelScope.launch {
                val count = contentRepository.getFeaturedArticles().size
                _uiState.value = _uiState.value.copy(
                    description = "Base inicial com $count artigo(s) fake e rota para detalhe parametrizado.",
                )
            }
        }
    }
}
