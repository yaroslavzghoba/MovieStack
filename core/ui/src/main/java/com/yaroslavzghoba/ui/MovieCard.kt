package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.ui.util.Constants
import com.yaroslavzghoba.ui.util.PosterQuality
import com.yaroslavzghoba.ui.util.round
import com.yzghoba.achromatic.AchromaticTheme
import com.yzghoba.achromatic.components.AchromaticCard
import com.yzghoba.achromatic.components.AchromaticFilledTonalIconToggleButton

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    posterQuality: PosterQuality = PosterQuality.W342
) {
    val posterBaseUrl = "${Constants.POSTER_BASE_URL}${posterQuality.path}"
    var checked by remember { mutableStateOf(false) }
    AchromaticCard(
        modifier = modifier,
    ) {
        Box {
            AsyncImage(
                model = "$posterBaseUrl${movie.posterPath}",
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
                    text = "${movie.voteAverage.round(decimals = 1)}/10",
                    style = MaterialTheme.typography.titleSmall,
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = null  // TODO: Get description from resources
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    AchromaticTheme {
        MovieCard(
            movie = movie,
            modifier = Modifier.padding(16.dp)
        )
    }
}

private val movie = Movie(
    adult = false,
    backdropPath = "/fqv8v6AycXKsivp1T5yKtLbGXce.jpg",
    genreIds = listOf(878, 12, 28),
    id = 653346,
    originalLanguage = "en",
    originalTitle = "Kingdom of the Planet of the Apes",
    overview = "Several generations in the future following Caesar's reign, apes are now the dominant species and live harmoniously while humans have been reduced to living in the shadows. As a new tyrannical ape leader builds his empire, one young ape undertakes a harrowing journey that will cause him to question all that he has known about the past and to make choices that will define a future for apes and humans alike.",
    popularity = 8377.115,
    posterPath = "/gKkl37BQuKTanygYQG1pyYgLVgf.jpg",
    releaseDate = "2024-05-08",
    title = "Kingdom of the Planet of the Apes",
    video = false,
    voteAverage = 6.995,
    voteCount = 719,
    cacheId = 0,
)