package com.yaroslavzghoba.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.home.component.MovieSection
import com.yaroslavzghoba.home.util.MovieSectionItem
import com.yaroslavzghoba.ui.MovieBottomSheet

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onGetMoreDiscover: () -> Unit,
    onGetMoreNowPlaying: () -> Unit,
    onGetMorePopular: () -> Unit,
    onGetMoreTopRated: () -> Unit,
    onGetMoreUpcoming: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sections = listOf(
        MovieSectionItem(
            titleRes = R.string.popular_section_title,
            contentType = "Popular movies",
            movies = viewModel.popularMovies.collectAsLazyPagingItems(),
            onGetMoreMovies = onGetMorePopular,
        ),
        MovieSectionItem(
            titleRes = R.string.top_rated_section_title,
            contentType = "Top rated movies",
            movies = viewModel.topRatedMovies.collectAsLazyPagingItems(),
            onGetMoreMovies = onGetMoreTopRated,
        ),
        MovieSectionItem(
            titleRes = R.string.now_playing_section_title,
            contentType = "Now playing movies",
            movies = viewModel.nowPlayingMovies.collectAsLazyPagingItems(),
            onGetMoreMovies = onGetMoreNowPlaying,
        ),
        MovieSectionItem(
            titleRes = R.string.upcoming_section_title,
            contentType = "Upcoming movies",
            movies = viewModel.upcomingMovies.collectAsLazyPagingItems(),
            onGetMoreMovies = onGetMoreUpcoming,
        ),
        MovieSectionItem(
            titleRes = R.string.discover_section_title,
            contentType = "Discover movies",
            movies = viewModel.discoverMovies.collectAsLazyPagingItems(),
            onGetMoreMovies = onGetMoreDiscover,
        ),
    )

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Movie details bottom sheet
        viewModel.selectedMovie?.let { selectedMovie ->
            MovieBottomSheet(
                movie = selectedMovie,
                onDismissRequest = {
                    viewModel.onEvent(event = HomeUiEvent.BottomSheetDismissed)
                },
            )
        }

        // Movie sections
        Spacer(modifier = Modifier.height(8.dp))
        sections.forEach { section ->
            MovieSection(
                titleRes = section.titleRes,
                contentType = section.contentType,
                movies = section.movies,
                onViewAboutMovie = { movie ->
                    viewModel.onEvent(event = HomeUiEvent.MovieDetails(movie = movie))
                },
                onMoveToWished = { movie ->
                    viewModel.onEvent(event = HomeUiEvent.MoveMovieToWished(movie = movie))
                },
                onMoveToWatched = { movie ->
                    viewModel.onEvent(event = HomeUiEvent.MoveMovieToWatched(movie = movie))
                },
                onGetMoreMovies = section.onGetMoreMovies,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}