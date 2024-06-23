package com.yaroslavzghoba.movie_details

import android.content.ActivityNotFoundException
import android.content.Context
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
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
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
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getMovieDetails(id = id)
                .collectLatest { requestResult ->
                    result = requestResult
                    val movie = requestResult.getOrNull() ?: return@collectLatest
                    val numWatchedMovies = repository.countWatchedMoviesById(id = movie.id)
                    val numWishedMovies = repository.countWishedMoviesById(id = movie.id)
                    numWatchedMovies
                        .combine(numWishedMovies) { flow1, flow2 -> listOf(flow1, flow2) }
                        .map { numMovies -> numMovies.map { it > 0 } }
                        .collect {
                            isWatchedMovie = it.first()
                            isWishedMovie = it.last()
                        }
                }
        }
    }

    internal fun onEvent(event: MovieDetailsUiEvent) {
        when (event) {
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
//            putExtra(CalendarContract.Events.DESCRIPTION, movie.overview)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTimeMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeMillis)
            putExtra(CalendarContract.Events.ALL_DAY, false)  // periodicity
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(
                context,
                context.getString(R.string.calendar_not_found),
                Toast.LENGTH_LONG,
            ).show()
        }
    }
}