package com.yaroslavzghoba.watched_movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.ui.MovieCard

@Composable
fun WatchedScreen(
    viewModel: WatchedViewModel,
    onGetMovieDetails: (WatchedMovie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(spaceBetweenCards),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
    ) {
        items(viewModel.movies) { movie ->
            MovieCard(
                movie = movie,
                onCardClicked = { onGetMovieDetails(movie) },
                modifier = Modifier,
            )
        }
    }
}