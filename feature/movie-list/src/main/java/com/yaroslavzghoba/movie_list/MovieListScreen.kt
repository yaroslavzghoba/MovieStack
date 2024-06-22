package com.yaroslavzghoba.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.movie_list.component.MovieListContent
import com.yaroslavzghoba.movie_list.component.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onReturnBack: () -> Unit,
    onGetMovieDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Column(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Includes status bar padding
        TopBar(
            titleRes = viewModel.titleRes,
            onReturnBack = onReturnBack,
            scrollBehavior = scrollBehavior,
        )

        MovieListContent(
            movies = movies,
            contentType = viewModel.contentType,
            onViewAboutMovie = { onGetMovieDetails(it.id) },
            modifier = Modifier.weight(1f),
        )
    }
}