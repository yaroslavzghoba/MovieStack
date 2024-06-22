package com.yaroslavzghoba.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.yaroslavzghoba.home.R
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.ui.MovieCard

@Composable
internal fun MovieSection(
    @StringRes titleRes: Int,
    contentType: String,
    movies: LazyPagingItems<Movie>,
    onGetMovieDetails: (Int) -> Unit,
    onGetMoreMovies: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier = modifier) {
        MovieSectionHeader(
            title = stringResource(id = titleRes),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onGetMoreMovies,
                ),
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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
                        onCardClicked = { onGetMovieDetails(movie.id) },
                        modifier = Modifier.widthIn(max = 160.dp)
                    )
                }
            }
            item { CircularProgressIndicator() }
        }
    }
}

@Composable
fun MovieSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = stringResource(id = R.string.get_more_movies_btn_description),
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}