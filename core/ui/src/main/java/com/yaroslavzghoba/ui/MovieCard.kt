package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yaroslavzghoba.common.LocalPosterQuality
import com.yaroslavzghoba.common.PosterQuality
import com.yaroslavzghoba.common.round
import com.yaroslavzghoba.model.MovieCommon
import com.yaroslavzghoba.ui.util.Constants

@Composable
fun MovieCard(
    movie: MovieCommon,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    additionalActionButton: @Composable (() -> Unit)? = null,
    posterQuality: PosterQuality = LocalPosterQuality.current,
) {
    val posterUrl = "${Constants.IMAGE_BASE_URL}${posterQuality.path}${movie.posterPath}"
    Card(
        onClick = onCardClicked,
        modifier = modifier,
    ) {
        Box {
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1.5f),
                placeholder = painterResource(id = R.drawable.movie_poster_placeholder),
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                additionalActionButton?.let { it() }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Text(
                text = movie.title,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.labelSmall
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${movie.voteAverage.round(1)}/10",
                    style = MaterialTheme.typography.titleSmall,
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = stringResource(
                        id = R.string.movie_evaluation_description,
                    ),
                )
            }
        }
    }
}