package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yaroslavzghoba.common.BackdropQuality
import com.yaroslavzghoba.common.LocalBackdropQuality
import com.yaroslavzghoba.model.MovieCommon
import com.yaroslavzghoba.ui.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBottomSheet(
    movie: MovieCommon,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    backdropQuality: BackdropQuality = LocalBackdropQuality.current
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        dragHandle = null,
    ) {
        movie.backdropPath?.let { backdropPath ->
            val backdropUrl = "${Constants.IMAGE_BASE_URL}${backdropQuality.path}${backdropPath}"
            AsyncImage(
                model = backdropUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = movie.title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
                if (movie.adult) {
                    Surface(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.errorContainer,
                    ) {
                        Text(
                            text = stringResource(id = R.string.adult_movie_label),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.bodyLarge
                                .copy(color = MaterialTheme.colorScheme.onErrorContainer),
                        )
                    }
                }
            }
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.labelMedium
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${movie.voteAverage}/10",
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = stringResource(
                        id = R.string.movie_evaluation_description,
                    )
                )
            }
            Text(
                text = movie.overview,
//                textAlign = TextAlign.Justify,
            )
        }
    }
}