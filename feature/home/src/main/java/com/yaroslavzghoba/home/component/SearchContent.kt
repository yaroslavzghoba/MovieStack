package com.yaroslavzghoba.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.ui.MovieCard

@Composable
fun SearchContent(
    movies: LazyPagingItems<Movie>,
    onViewAboutMovie: (Movie) -> Unit,
    onMoveToWished: (Movie) -> Unit,
    onMoveToWatched: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(spaceBetweenCards),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
    ) {
        items(
            count = movies.itemCount,
            key = movies.itemKey { it.cacheId },
            contentType = movies.itemContentType { "searchedMovies" }
        ) { index ->
            movies[index]?.let { movie ->
                MovieCard(
                    movie = movie,
                    onCardClicked = { onViewAboutMovie(movie) },
                    additionalActionButton = {
                        AdditionalActionButton(
                            onViewAboutMovie = { onViewAboutMovie(movie) },
                            onMoveToWished = { onMoveToWished(movie) },
                            onMoveToWatched = { onMoveToWatched(movie) }
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            }
        }
        item {
            // Next page loading indicator
            if (movies.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}