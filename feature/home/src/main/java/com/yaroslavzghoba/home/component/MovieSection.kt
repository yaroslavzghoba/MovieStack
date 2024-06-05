package com.yaroslavzghoba.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.yaroslavzghoba.home.R
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.ui.MovieCard

@Composable
internal fun MovieSection(
    title: String,
    movies: LazyPagingItems<Movie>,
    onGetMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spaceBetweenCards = 8.dp
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onGetMore,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null,
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spaceBetweenCards),
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
        ) {
            item { Spacer(modifier = Modifier.width(spaceBetweenCards)) }
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.cacheId },
                contentType = movies.itemContentType { title }
            ) { index ->
                movies[index]?.let { movie ->
                    MovieCard(
                        movie = movie,
                        modifier = Modifier.widthIn(max = 150.dp)
                    )
                }
            }
            item {
                // TODO: Implement circular progress indicator
                Spacer(modifier = Modifier.width(spaceBetweenCards))
            }
        }
//        Row(
//            modifier = Modifier.padding(vertical = spaceBetweenCards),
//            horizontalArrangement = Arrangement.spacedBy(spaceBetweenCards),
//        ) {
//            Spacer(modifier = Modifier.width(spaceBetweenCards))
//
//            Spacer(modifier = Modifier.width(spaceBetweenCards))
//        }
    }
}