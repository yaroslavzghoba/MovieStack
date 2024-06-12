package com.yaroslavzghoba.movie_list.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalIconButton
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
import com.yaroslavzghoba.movie_list.R

@Composable
internal fun AdditionalActionButton(
    onViewAboutMovie: () -> Unit,
    onMoveToWished: () -> Unit,
    onMoveToWatched: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDropdownMenuExpanded by remember { mutableStateOf(false) }
    FilledTonalIconButton(
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
        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.view_about_movie_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onViewAboutMovie()
            },
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.move_to_wished_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onMoveToWished()
            },
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(id = R.string.move_to_watched_action)) },
            onClick = {
                isDropdownMenuExpanded = false
                onMoveToWatched()
            },
        )
    }
}