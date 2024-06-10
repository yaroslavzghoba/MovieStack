package com.yaroslavzghoba.watched_movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedViewModel @Inject constructor(
    private val repository: ApplicationRepository,
) : ViewModel() {

    internal var movies by mutableStateOf(emptyList<WatchedMovie>())
        private set
    internal var selectedMovie by mutableStateOf<WatchedMovie?>(null)
        private set

    init {
        viewModelScope.launch {
            repository.getAllWatchedMovies().collect {
                movies = it
            }
        }
    }

    internal fun onEvent(event: WatchedUiEvent) {
        when (event) {
            is WatchedUiEvent.BottomSheetDismissed -> {
                selectedMovie = null
            }

            is WatchedUiEvent.MovieDetails -> {
                selectedMovie = event.movie
            }

            is WatchedUiEvent.MoveMovieToWished -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.moveWatchedMovieToWishedMovies(movie = event.movie)
                }
            }

            is WatchedUiEvent.DeleteMovie -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.deleteWatchedMovie(movie = event.movie)
                }
            }
        }
    }
}