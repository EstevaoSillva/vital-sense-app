package com.mindsense.feature.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindsense.domain.model.CollectionDetail
import com.mindsense.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val historyRepository: HistoryRepository,
) : ViewModel() {

    private val collectionId: String = savedStateHandle["collectionId"] ?: "1"
    private val _detail = MutableStateFlow<CollectionDetail?>(null)
    val detail: StateFlow<CollectionDetail?> = _detail.asStateFlow()

    init {
        viewModelScope.launch {
            _detail.value = historyRepository.getCollectionDetail(collectionId)
        }
    }
}
