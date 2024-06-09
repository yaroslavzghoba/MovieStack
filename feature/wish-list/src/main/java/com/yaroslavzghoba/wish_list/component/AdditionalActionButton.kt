package com.yaroslavzghoba.wish_list.component

import androidx.compose.material3.DropdownMenu
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
import com.yaroslavzghoba.model.WishedMovie
import com.yaroslavzghoba.wish_list.R
import com.yaroslavzghoba.wish_list.WishListUiEvent
import com.yaroslavzghoba.wish_list.WishListViewModel
import com.yzghoba.achromatic.components.AchromaticDropdownMenuItem
import com.yzghoba.achromatic.components.AchromaticFilledTonalIconButton

@Composable
internal fun AdditionalActionButton(
    movie: WishedMovie,
    viewModel: WishListViewModel,
    modifier: Modifier = Modifier,
) {
    AdditionalActionButton(
        onViewAboutMovie = {
            viewModel.onEvent(
                event = WishListUiEvent.MovieDetails(movie = movie),
            )
        },
        onMoveToWatched = {
            viewModel.onEvent(
                event = WishListUiEvent.MoveMovieToWatched(movie = movie),
            )
        },
        onDeleteMovie = {
            viewModel.onEvent(
                event = WishListUiEvent.DeleteMovie(movie = movie),
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun AdditionalActionButton(
    onViewAboutMovie: () -> Unit,
    onMoveToWatched: () -> Unit,
    onDeleteMovie: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDropdownMenuExpanded by remember { mutableStateOf(false) }
    AchromaticFilledTonalIconButton(
        onClick = { isDropdownMenuExpanded = true },
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_more_vert_24),
            contentDescription = stringResource(id = R.string.more_actions_btn_description),
        )
    }
    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = { isDropdownMenuExpanded = false },
    ) {
        AchromaticDropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.view_about_movie_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onViewAboutMovie()
            },
        )
        AchromaticDropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.move_to_watched_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onMoveToWatched()
            },
        )
        AchromaticDropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.delete_movie_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onDeleteMovie()
            },
        )
    }
}