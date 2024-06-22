package com.yaroslavzghoba.movie_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "ExceptionsAreMyBlood"

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ApplicationRepository,
) : ViewModel() {

    private val id = savedStateHandle.toRoute<Screen.MovieDetails>().id
    var result by mutableStateOf<Result<MovieDetails>?>(null)
        private set
    var isWatchedMovie by mutableStateOf(false)
        private set
    var isWishedMovie by mutableStateOf(false)
        private set

    init {
        Log.d(TAG, "Movie id extracted from the route: $id")
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getMovieDetails(id = id)
                .collectLatest { requestResult ->
                    Log.d(TAG, "Request result was collected: $requestResult")
                    result = requestResult
                    val movie = requestResult.getOrNull() ?: return@collectLatest
                    val numWatchedMovies = repository.countWatchedMoviesById(id = movie.id)
                    val numWishedMovies = repository.countWishedMoviesById(id = movie.id)
                    numWatchedMovies
                        .combine(numWishedMovies) { f1, f2 -> listOf(f1, f2) }
                        .map { nums -> nums.map { it > 0 } }
                        .collect {
                            isWatchedMovie = it[0]
                            isWishedMovie = it[1]
                        }
                }
        }
    }

    internal fun onEvent(event: MovieDetailsUiEvent) {
        Log.d(TAG, "onEvent call. event: $event")
        when (event) {
            MovieDetailsUiEvent.AddMovieToWatched -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.addMovieToWatched(movie = movie)
                }
            }

            MovieDetailsUiEvent.AddMovieToWishList -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.addMovieToWished(movie = movie)
                }
            }

            MovieDetailsUiEvent.MoveMovieToWatched -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.moveWishedMovieToWatched(movie = movie)
                }
            }

            MovieDetailsUiEvent.MoveMovieToWishList -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.moveWatchedMovieToWished(movie = movie)
                }
            }

            MovieDetailsUiEvent.RemoveMovieFromWatched -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.deleteWatchedMoviesById(id = movie.id)
                }
            }

            MovieDetailsUiEvent.RemoveMovieFromWishList -> {
                val movie = result?.getOrNull() ?: return
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.deleteWishedMoviesById(id = movie.id)
                }
            }
        }
    }
}