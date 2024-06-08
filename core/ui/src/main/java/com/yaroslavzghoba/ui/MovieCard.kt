package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.ui.util.Constants
import com.yzghoba.achromatic.components.AchromaticCard
import com.yzghoba.achromatic.components.AchromaticFilledTonalIconToggleButton
import com.yzghoba.achromatic.components.cardAchromaticColors

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    posterQuality: PosterQuality = LocalPosterQuality.current,
) {
    val posterUrl = "${Constants.IMAGE_BASE_URL}${posterQuality.path}${movie.posterPath}"
    var checked by remember { mutableStateOf(false) }
    AchromaticCard(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardAchromaticColors(),
        elevation = CardDefaults.elevatedCardElevation()
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
            AchromaticFilledTonalIconToggleButton(
                checked = checked,
                onCheckedChange = { checked = !checked },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(
                        id = when (checked) {
                            true -> R.drawable.baseline_favorite_24
                            false -> R.drawable.baseline_favorite_border_24
                        }
                    ),
                    contentDescription = null
                )
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
                        id = R.string.ui_movie_evaluation_description,
                    ),
                )
            }
        }
    }
}