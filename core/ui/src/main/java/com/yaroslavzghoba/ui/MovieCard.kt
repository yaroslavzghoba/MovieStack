package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yaroslavzghoba.common.LocalPosterQuality
import com.yaroslavzghoba.common.round
import com.yaroslavzghoba.model.MovieCommon
import com.yaroslavzghoba.model.util.PosterQuality
import com.yaroslavzghoba.ui.util.Constants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun MovieCard(
    movie: MovieCommon,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    posterQuality: PosterQuality = LocalPosterQuality.current,
) {
    val posterUrl = "${Constants.IMAGE_BASE_URL}${posterQuality.path}${movie.posterPath}"
    val dateFormat = LocalDate.Format { byUnicodePattern(Constants.DATE_FORMAT_PATTERN) }
    val releaseDate = movie.releaseDate
    val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
    val isReleased = releaseDate?.let { it <= today } ?: false
    val evaluation = movie.voteAverage
        ?.let { if (isReleased) it.round(1).toString() else null } ?: "-"
    Card(
        onClick = onCardClicked,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1.5f),
            placeholder = painterResource(id = R.drawable.movie_poster_placeholder),
            contentScale = ContentScale.Crop,
        )
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
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = releaseDate?.format(dateFormat) ?: Constants.DATE_FORMAT_PATTERN,
                style = MaterialTheme.typography.labelSmall
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$evaluation/10",
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