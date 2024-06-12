package com.yaroslavzghoba.wish_list

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
import com.yaroslavzghoba.model.WishedMovie
import com.yaroslavzghoba.ui.MovieBottomSheet
import com.yaroslavzghoba.ui.MovieCard
import com.yaroslavzghoba.wish_list.component.AdditionalActionButton

@Composable
fun WishListScreen(
    viewModel: WishListViewModel,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    val onViewAboutMovie: (WishedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WishListUiEvent.MovieDetails(movie = movie),
        )
    }
    val onMoveToWatched: (WishedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WishListUiEvent.MoveMovieToWatched(movie = movie),
        )
    }
    val onDeleteMovie: (WishedMovie) -> Unit = { movie ->
        viewModel.onEvent(
            event = WishListUiEvent.DeleteMovie(movie = movie),
        )
    }

    Surface(modifier = modifier) {
        // Bottom sheet with movie details
        viewModel.selectedMovie?.let { selectedMovie ->
            MovieBottomSheet(
                movie = selectedMovie,
                onDismissRequest = {
                    viewModel.onEvent(event = WishListUiEvent.BottomSheetDismissed)
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
                            event = WishListUiEvent.MovieDetails(movie = movie)
                        )
                    },
                    modifier = modifier,
                    additionalActionButton = {
                        AdditionalActionButton(
                            onViewAboutMovie = { onViewAboutMovie(movie) },
                            onMoveToWatched = { onMoveToWatched(movie) },
                            onDeleteMovie = { onDeleteMovie(movie) },
                        )
                    },
                )
            }
        }
    }
}