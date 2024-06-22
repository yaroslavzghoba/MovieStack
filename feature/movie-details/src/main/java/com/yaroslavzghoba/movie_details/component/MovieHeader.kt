package com.yaroslavzghoba.movie_details.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yaroslavzghoba.model.MovieDetails
import com.yaroslavzghoba.movie_details.R

@Composable
internal fun MovieHeader(
    movie: MovieDetails?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (movie?.adult == true) {
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
        Text(
            text = movie?.title ?: "",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineMedium
                .copy(color = MaterialTheme.colorScheme.onSurface),
        )
    }
}