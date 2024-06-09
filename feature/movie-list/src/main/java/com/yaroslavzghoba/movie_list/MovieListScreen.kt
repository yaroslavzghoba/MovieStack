package com.yaroslavzghoba.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.movie_list.component.MovieListContent
import com.yaroslavzghoba.movie_list.component.MovieListTopBar
import com.yaroslavzghoba.ui.MovieBottomSheet
import com.yzghoba.achromatic.AchromaticTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onReturnBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Column(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(AchromaticTheme.colorScheme.background),
    ) {
        // Bottom sheet with movie details
        viewModel.selectedMovie?.let { selectedMovie ->
            MovieBottomSheet(
                movie = selectedMovie,
                onDismissRequest = {
                    viewModel.onEvent(event = MovieListUiEvent.BottomSheetDismissed)
                },
            )
        }

        MovieListTopBar(
            titleRes = viewModel.titleRes,
            onReturnBack = onReturnBack,
            scrollBehavior = scrollBehavior,
        )

        MovieListContent(
            movies = movies,
            contentType = viewModel.contentType,
            onViewAboutMovie = { movie ->
                viewModel.onEvent(event = MovieListUiEvent.MovieDetails(movie = movie))
            },
            onMoveToWished = { movie ->
                viewModel.onEvent(event = MovieListUiEvent.MoveMovieToWished(movie = movie))
            },
            onMoveToWatched = { movie ->
                viewModel.onEvent(event = MovieListUiEvent.MoveMovieToWatched(movie = movie))
            },
            modifier = Modifier.weight(1f),
        )
    }
}