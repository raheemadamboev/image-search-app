package xyz.teamgravity.imagesearchapp.api

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn

class UnsplashViewModel @ViewModelInject constructor(private val repository: UnsplashRepository, @Assisted state: SavedStateHandle) :
    ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap { currentQuery ->
        repository.getSearchResults(currentQuery).cachedIn(viewModelScope)
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "cars"
    }

    fun searchPhoto(query: String) {
        currentQuery.value = query
    }
}