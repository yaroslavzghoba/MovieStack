package com.yaroslavzghoba.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApplicationRepository,
) : ViewModel() {

    private var job: Job? = null

    /**A list of discover movies that can be updated*/
    var searchedMovies: Flow<PagingData<Movie>> by mutableStateOf(emptyFlow())

    /**A list of discover movies that can be updated*/
    val discoverMovies = repository.getDiscoverMovies()
        .cachedIn(viewModelScope)

    /**A list of popular movies that can be updated*/
    val popularMovies = repository.getPopularMovies()
        .cachedIn(viewModelScope)

    /**A list of top rated movies that can be updated*/
    val topRatedMovies = repository.getTopRatedMovies()
        .cachedIn(viewModelScope)

    /**A list of now playing movies that can be updated*/
    val nowPlayingMovies = repository.getNowPlayingMovies()
        .cachedIn(viewModelScope)

    /**A list of upcoming movies that can be updated*/
    val upcomingMovies = repository.getUpcomingMovies()
        .cachedIn(viewModelScope)


    internal fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchMoviesRequest -> {
                job?.cancel()
                event.query.ifBlank { return }
                job = viewModelScope.launch(context = Dispatchers.IO) {
                    searchedMovies = repository.getMoviesByQuery(query = event.query)
                        .cachedIn(viewModelScope)
                }
            }
        }
    }
}