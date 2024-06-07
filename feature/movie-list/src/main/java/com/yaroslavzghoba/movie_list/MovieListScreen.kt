package com.yaroslavzghoba.movie_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.movie_list.component.MovieListContent
import com.yaroslavzghoba.movie_list.component.MovieListTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onReturnBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MovieListTopBar(
                titleRes = viewModel.titleRes,
                onReturnBack = onReturnBack,
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPaddings ->
        MovieListContent(
            movies = movies,
            contentType = viewModel.contentType,
            modifier = Modifier.padding(innerPaddings),
        )
    }
}