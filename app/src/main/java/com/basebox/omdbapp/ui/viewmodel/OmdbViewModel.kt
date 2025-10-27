package com.basebox.omdbapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basebox.omdbapp.data.local.entity.MovieEntity
import com.basebox.omdbapp.data.repository.Repository
import com.basebox.omdbapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OmdbViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieEntity?>(null)
    val movieDetail: StateFlow<MovieEntity?> = _movieDetail

    private val _selectedMovieId = MutableStateFlow<String?>(null)
    val selectedMovieId: StateFlow<String?> = _selectedMovieId

    private val _isLoading = MutableStateFlow<Resource<Unit>>(Resource.Success(Unit))
    val isLoading: StateFlow<Resource<Unit>> = _isLoading
    val cachedMovies: StateFlow<List<MovieEntity>> =
        repo.cachedMovies().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _searchState = MutableStateFlow<Resource<Unit>>(Resource.Success(Unit))
    val searchState: StateFlow<Resource<Unit>> = _searchState.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(1000L)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isNotBlank()) {
                        search(query.trim())
                    } else {
                        _searchState.value = Resource.Success(Unit)
                    }
                }
        }
    }

    fun selectMovie(imdbId: String) {
        _selectedMovieId.value = imdbId
        getDetail(imdbId, {})
    }

    fun clearSelectedMovie() {
        _selectedMovieId.value = null
        _movieDetail.value = null
        _isLoading.value = Resource.Success(Unit)
    }
    fun onQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }
    fun search(query: String) {
        viewModelScope.launch {
            _searchState.value = Resource.Loading
            val res = repo.searchAndCache(query)
            _searchState.value = res.fold(
                onSuccess = { Resource.Success(Unit) },
                onFailure = { Resource.Error(it.message ?: "Unknown error", it) }
            )
        }
    }

    fun getDetail(imdbId: String, onResult: (Resource<MovieEntity?>) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = Resource.Loading
            val r = repo.getMovieDetail(imdbId)
            _isLoading.value = r.fold({ Resource.Success(Unit) },
                { Resource.Error(it.message, it) })
            _movieDetail.value = r.getOrNull()
        }
    }
}