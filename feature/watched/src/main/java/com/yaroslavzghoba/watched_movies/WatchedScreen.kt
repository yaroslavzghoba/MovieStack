package com.yaroslavzghoba.watched_movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.ui.MovieBottomSheet
import com.yaroslavzghoba.ui.MovieCard
import com.yaroslavzghoba.watched_movies.component.AdditionalActionButton

@Composable
fun WatchedScreen(
    viewModel: WatchedViewModel,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    val onViewAboutMovie: (WatchedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WatchedUiEvent.MovieDetails(movie = movie),
        )
    }
    val onMoveToWished: (WatchedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WatchedUiEvent.MoveMovieToWished(movie = movie),
        )
    }
    val onDeleteMovie: (WatchedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WatchedUiEvent.DeleteMovie(movie = movie),
        )
    }

    Surface(modifier = modifier) {
        // Bottom sheet with movie details
        viewModel.selectedMovie?.let { selectedMovie ->
            MovieBottomSheet(
                movie = selectedMovie,
                onDismissRequest = {
                    viewModel.onEvent(event = WatchedUiEvent.BottomSheetDismissed)
                },
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(spaceBetweenCards),
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
        ) {
            items(viewModel.movies) { movie ->
                MovieCard(
                    movie = movie,
                    onCardClicked = {
                        viewModel.onEvent(
                            event = WatchedUiEvent.MovieDetails(movie = movie)
                        )
                    },
                    modifier = modifier,
                    additionalActionButton = {
                        AdditionalActionButton(
                            onViewAboutMovie = { onViewAboutMovie(movie) },
                            onMoveToWished = { onMoveToWished(movie) },
                            onDeleteMovie = { onDeleteMovie(movie) },
                        )
                    },
                )
            }
        }
    }
}