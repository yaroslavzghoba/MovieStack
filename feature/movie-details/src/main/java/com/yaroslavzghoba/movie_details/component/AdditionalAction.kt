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
    val onDismissRequest: () -> Unit = { isDropdownMenuExpanded = false }
    Box(modifier = modifier) {
        FilledTonalIconToggleButton(
            checked = isDropdownMenuExpanded,
            onCheckedChange = { isDropdownMenuExpanded = it }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_more_vert_24),
                contentDescription = null,
            )
        }
        when {
            !isWatchedMovie && !isWishedMovie -> {
                NotWatchedNotWishedMenu(
                    viewModel = viewModel,
                    isDropdownMenuExpanded = isDropdownMenuExpanded,
                    onDismissRequest = onDismissRequest,
                )
            }

            !isWatchedMovie && isWishedMovie -> {
                NotWatchedButWishedMenu(
                    viewModel = viewModel,
                    isDropdownMenuExpanded = isDropdownMenuExpanded,
                    onDismissRequest = onDismissRequest,
                )
            }

            isWatchedMovie && !isWishedMovie -> {
                WatchedButNotWishedMenu(
                    viewModel = viewModel,
                    isDropdownMenuExpanded = isDropdownMenuExpanded,
                    onDismissRequest = onDismissRequest,
                )
            }

            isWatchedMovie && isWishedMovie -> {
                WatchedAndWishedMenu(
                    viewModel = viewModel,
                    isDropdownMenuExpanded = isDropdownMenuExpanded,
                    onDismissRequest = onDismissRequest,
                )
            }
        }
    }
}

@Composable
private fun NotWatchedNotWishedMenu(
    viewModel: MovieDetailsViewModel,
    isDropdownMenuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.add_movie_to_wish_list_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.AddMovieToWishList)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.add_movie_to_watched_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.AddMovieToWatched)
                onDismissRequest()
            }
        )
    }
}

@Composable
private fun NotWatchedButWishedMenu(
    viewModel: MovieDetailsViewModel,
    isDropdownMenuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.move_movie_to_watched_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.MoveMovieToWatched)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.remove_movie_from_wish_list_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.RemoveMovieFromWishList)
                onDismissRequest()
            }
        )
    }
}

@Composable
private fun WatchedButNotWishedMenu(
    viewModel: MovieDetailsViewModel,
    isDropdownMenuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.move_movie_to_wish_list_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.MoveMovieToWishList)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.remove_movie_from_watched_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.RemoveMovieFromWatched)
                onDismissRequest()
            }
        )
    }
}

@Composable
private fun WatchedAndWishedMenu(
    viewModel: MovieDetailsViewModel,
    isDropdownMenuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.remove_movie_from_wish_list_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.RemoveMovieFromWishList)
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = {
                @StringRes val textRes = R.string.remove_movie_from_watched_option
                Text(text = stringResource(textRes))
            },
            onClick = {
                viewModel.onEvent(event = MovieDetailsUiEvent.RemoveMovieFromWatched)
                onDismissRequest()
            }
        )
    }
}