package com.example.imagesearcapp.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.imagesearcapp.data.Repository

class GalleryViewModel@ViewModelInject constructor(
    private  val repository: Repository,
    @Assisted state: SavedStateHandle
): ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap {queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)

    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private  const val DEFAULT_QUERY = "cats"
        private  const val CURRENT_QUERY = "current-query"
    }
}