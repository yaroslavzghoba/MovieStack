package com.yaroslavzghoba.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yaroslavzghoba.model.MovieDetails
import com.yaroslavzghoba.movie_details.component.AdditionalAction
import com.yaroslavzghoba.movie_details.component.MovieHeader
import com.yaroslavzghoba.movie_details.util.Constants
import com.yaroslavzghoba.ui.MovieBackdrop
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onReturnBackRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movie = viewModel.result?.getOrNull()
    val dateFormat = LocalDate.Format { byUnicodePattern(Constants.DATE_FORMAT_PATTERN) }
    val releaseDate = movie?.releaseDate?.format(dateFormat)
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
        ),
    ) {
        BackdropWishActions(
            viewModel = viewModel,
            onReturnBackRequest = onReturnBackRequest,
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            MovieHeader(
                movie = movie,
                modifier = Modifier.wrapContentSize(),
            )
            Text(
                text = releaseDate ?: "",
                style = MaterialTheme.typography.labelMedium
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
            MovieEvaluation(
                movie = movie,
                modifier = Modifier.align(Alignment.End),
            )
            Text(
                text = movie?.overview ?: "",
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun BackdropWishActions(
    viewModel: MovieDetailsViewModel,
    onReturnBackRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movie = viewModel.result?.getOrNull()
    val actionPaddings = 8.dp
    Box(modifier = modifier) {
        MovieBackdrop(
            backdropPath = movie?.backdropPath,
            modifier = Modifier.fillMaxWidth()
        )
        FilledTonalIconButton(
            onClick = onReturnBackRequest,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(actionPaddings),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = stringResource(id = R.string.return_back_btn_description),
            )
        }
        AdditionalAction(
            viewModel = viewModel,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(actionPaddings),
        )
    }
}

@Composable
private fun MovieEvaluation(
    movie: MovieDetails?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = movie?.voteAverage?.let { "${it}/10" } ?: "",
            style = MaterialTheme.typography.titleLarge
                .copy(color = MaterialTheme.colorScheme.onSurface),
        )
        Icon(
            painter = painterResource(id = com.yaroslavzghoba.ui.R.drawable.baseline_star_24),
            contentDescription = stringResource(id = R.string.movie_evaluation_description),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}