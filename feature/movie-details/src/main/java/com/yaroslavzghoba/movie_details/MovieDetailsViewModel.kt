package com.yaroslavzghoba.movie_details

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val context: Application,
    private val repository: ApplicationRepository,
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<Screen.MovieDetails>().id

    /**The result of executing movie details request*/
    var result by mutableStateOf<Result<MovieDetails>?>(null)
        private set

    /**Indicates is the requested movie saved in watched*/
    var isWatchedMovie by mutableStateOf(false)
        private set

    /**Indicates is the requested movie saved in wished*/
    var isWishedMovie by mutableStateOf(false)
        private set

    init {
        // Get movie by movie id
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getMovieDetails(id = movieId)
                .collectLatest movieCollector@{ requestResult ->
                    result = requestResult
                }
        }

        // Check is the current movie saved in wished
        viewModelScope.launch {
            repository.countWishedMoviesById(id = movieId)
                .map { moviesNum -> moviesNum > 0 }
                .collectLatest { isWished ->
                    isWishedMovie = isWished
                }
        }

        // Check is the current movie saved in watched
        viewModelScope.launch {
            repository.countWatchedMoviesById(id = movieId)
                .map { moviesNum -> moviesNum > 0 }
                .collectLatest { isWatched ->
                    isWatchedMovie = isWatched
                }
        }
    }

    internal fun onEvent(event: MovieDetailsUiEvent) {
        when (event) {
//            MovieDetailsUiEvent.NotificationsGrated -> {
//                repository.launchMovieReleaseNotifications()
//            }

            MovieDetailsUiEvent.ViewingScheduling -> {
                val movie = result?.getOrNull() ?: return
                val timeOffset = DateTimePeriod(minutes = 30)
                val timeZone = TimeZone.currentSystemDefault()
                val startTime = Clock.System.now().plus(timeOffset, timeZone)
                addMovieToCalendar(movie = movie, startTime = startTime)
            }

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

    private fun addMovieToCalendar(movie: MovieDetails, startTime: Instant) {
        val title = context.getString(R.string.calendar_event_title)
        val startTimeMillis = startTime.toEpochMilliseconds()
        val duration = DateTimePeriod(minutes = movie.runtime)
        val timeZone = TimeZone.currentSystemDefault()
        val endTimeMillis = startTime.plus(duration, timeZone).toEpochMilliseconds()

        val intent = Intent(Intent.ACTION_EDIT).apply {
            setType("vnd.android.cursor.item/event")
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.DESCRIPTION, movie.overview)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTimeMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeMillis)
            putExtra(CalendarContract.Events.ALL_DAY, false)  // periodicity
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (exception: ActivityNotFoundException) {
            val message = context.getString(R.string.calendar_not_found)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}