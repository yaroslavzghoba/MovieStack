package com.yaroslavzghoba.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApplicationRepository,
) : ViewModel() {

    internal var selectedMovie by mutableStateOf<Movie?>(null)
        private set

    /**A list of discover movies that can be updated*/
    internal val discoverMovies = repository.getDiscoverMovies()
        .cachedIn(viewModelScope)

    /**A list of popular movies that can be updated*/
    internal val popularMovies = repository.getPopularMovies()
        .cachedIn(viewModelScope)

    /**A list of top rated movies that can be updated*/
    internal val topRatedMovies = repository.getTopRatedMovies()
        .cachedIn(viewModelScope)

    /**A list of now playing movies that can be updated*/
    internal val nowPlayingMovies = repository.getNowPlayingMovies()
        .cachedIn(viewModelScope)

    /**A list of upcoming movies that can be updated*/
    internal val upcomingMovies = repository.getUpcomingMovies()
        .cachedIn(viewModelScope)


    internal fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.BottomSheetDismissed -> {
                selectedMovie = null
            }

            is HomeUiEvent.MovieDetails -> {
                selectedMovie = event.movie
            }

            is HomeUiEvent.MoveMovieToWished -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.moveMovieToWishedMovies(movie = event.movie)
                }
            }

            is HomeUiEvent.MoveMovieToWatched -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.moveMovieToWatchedMovies(movie = event.movie)
                }
            }
        }
    }
}