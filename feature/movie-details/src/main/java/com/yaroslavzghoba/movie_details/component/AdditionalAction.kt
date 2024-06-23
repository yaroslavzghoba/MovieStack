package com.yaroslavzghoba.movie_details.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.yaroslavzghoba.movie_details.MovieDetailsUiEvent
import com.yaroslavzghoba.movie_details.MovieDetailsViewModel
import com.yaroslavzghoba.movie_details.R

@Composable
internal fun AdditionalAction(
    viewModel: MovieDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val isWatchedMovie = viewModel.isWatchedMovie
    val isWishedMovie = viewModel.isWishedMovie
    var isDropdownMenuExpanded by remember { mutableStateOf(false) }
    val onMenuDismissRequest: () -> Unit = { isDropdownMenuExpanded = false }
    val onClick: (event: MovieDetailsUiEvent?) -> Unit = { event ->
        event?.let { viewModel.onEvent(event = it) }
        onMenuDismissRequest()
    }

    Box(modifier = modifier) {
        FilledTonalIconToggleButton(
            checked = isDropdownMenuExpanded,
            onCheckedChange = { isDropdownMenuExpanded = it },
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = isDropdownMenuExpanded,
            onDismissRequest = onMenuDismissRequest,
        ) {
            if (!isWatchedMovie && !isWishedMovie) {
                MovieDropdownMenuItem(
                    textRes = R.string.add_movie_to_wish_list_option,
                    onClick = { onClick(MovieDetailsUiEvent.AddMovieToWishList) },
                )
                MovieDropdownMenuItem(
                    textRes = R.string.add_movie_to_watched_option,
                    onClick = { onClick(MovieDetailsUiEvent.AddMovieToWatched) },
                )
            }
            if (!isWatchedMovie && isWishedMovie) {
                MovieDropdownMenuItem(
                    textRes = R.string.add_movie_to_your_calendar,
                    onClick = { onClick(MovieDetailsUiEvent.ViewingScheduling) },
                )
                MovieDropdownMenuItem(
                    textRes = R.string.move_movie_to_watched_option,
                    onClick = { onClick(MovieDetailsUiEvent.MoveMovieToWatched) },
                )
            }
            if (isWishedMovie) {
                MovieDropdownMenuItem(
                    textRes = R.string.remove_movie_from_wish_list_option,
                    onClick = { onClick(MovieDetailsUiEvent.AddMovieToWatched) },
                )
            }
            if (isWatchedMovie && !isWishedMovie) {
                MovieDropdownMenuItem(
                    textRes = R.string.move_movie_to_wish_list_option,
                    onClick = { onClick(MovieDetailsUiEvent.MoveMovieToWishList) },
                )
            }
            if (isWatchedMovie) {
                MovieDropdownMenuItem(
                    textRes = R.string.remove_movie_from_watched_option,
                    onClick = { onClick(MovieDetailsUiEvent.RemoveMovieFromWatched) },
                )
            }
        }
    }
}

@Composable
private fun MovieDropdownMenuItem(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenuItem(
        text = { Text(text = stringResource(textRes)) },
        onClick = onClick,
        modifier = modifier,
    )
}