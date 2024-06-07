package com.yaroslavzghoba.movie_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.yzghoba.achromatic.AchromaticTheme

@Composable
internal fun MovieListContent(
    movies: LazyPagingItems<Movie>,
    contentType: String,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier
                .background(color = AchromaticTheme.colorScheme.background),
            contentPadding = PaddingValues(spaceBetweenCards),
            verticalArrangement = Arrangement.spacedBy(spaceBetweenCards),
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards)
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.cacheId },
                contentType = movies.itemContentType { contentType }
            ) { index ->
                movies[index]?.let {
                    MovieCard(movie = it)
                }
            }
            item {
                Spacer(modifier = Modifier.height(spaceBetweenCards))

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