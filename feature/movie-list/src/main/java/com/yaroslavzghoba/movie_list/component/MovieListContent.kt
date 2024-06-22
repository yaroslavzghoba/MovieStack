package com.yaroslavzghoba.movie_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
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
internal fun MovieListContent(
    movies: LazyPagingItems<Movie>,
    contentType: String,
    onViewAboutMovie: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    Column(modifier = modifier) {
        // TODO: Show loading state here
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(spaceBetweenCards),
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.cacheId },
                contentType = movies.itemContentType { contentType }
            ) { index ->
                movies[index]?.let { movie ->
                    MovieCard(
                        movie = movie,
                        onCardClicked = { onViewAboutMovie(movie) },
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
}