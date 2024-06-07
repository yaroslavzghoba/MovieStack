package com.yaroslavzghoba.movie_list.component

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.yaroslavzghoba.movie_list.R
import com.yzghoba.achromatic.components.AchromaticLargeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieListTopBar(
    @StringRes titleRes: Int,
    onReturnBack: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    AchromaticLargeTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onReturnBack) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(
                        id = R.string.movie_list_return_back_description,
                    ),
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}