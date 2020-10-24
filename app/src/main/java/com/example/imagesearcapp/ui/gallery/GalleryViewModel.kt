package com.example.imagesearcapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.imagesearcapp.data.Repository

class GalleryViewModel@ViewModelInject constructor(
    private  val repository: Repository
): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap {queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)

    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private  const val DEFAULT_QUERY = "cats"
    }
}