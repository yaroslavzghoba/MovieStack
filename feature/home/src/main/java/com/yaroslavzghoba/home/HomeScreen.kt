package com.yaroslavzghoba.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.home.component.MovieSection
import com.yaroslavzghoba.home.util.MovieSectionItem
import com.yaroslavzghoba.ui.MovieBottomSheet
import com.yzghoba.achromatic.AchromaticTheme

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
            titleRes = R.string.home_popular_section_title,
            contentType = "Popular movies",
            movies = viewModel.popularMovies.collectAsLazyPagingItems(),
            onGetMoreClicked = onGetMorePopular,
        ),
        MovieSectionItem(
            titleRes = R.string.home_top_rated_section_title,
            contentType = "Top rated movies",
            movies = viewModel.topRatedMovies.collectAsLazyPagingItems(),
            onGetMoreClicked = onGetMoreTopRated,
        ),
        MovieSectionItem(
            titleRes = R.string.home_now_playing_section_title,
            contentType = "Now playing movies",
            movies = viewModel.nowPlayingMovies.collectAsLazyPagingItems(),
            onGetMoreClicked = onGetMoreNowPlaying,
        ),
        MovieSectionItem(
            titleRes = R.string.home_upcoming_section_title,
            contentType = "Upcoming movies",
            movies = viewModel.upcomingMovies.collectAsLazyPagingItems(),
            onGetMoreClicked = onGetMoreUpcoming,
        ),
        MovieSectionItem(
            titleRes = R.string.home_discover_section_title,
            contentType = "Discover movies",
            movies = viewModel.discoverMovies.collectAsLazyPagingItems(),
            onGetMoreClicked = onGetMoreDiscover,
        ),
    )

    Scaffold(
        modifier = modifier,
        containerColor = AchromaticTheme.colorScheme.background
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .verticalScroll(rememberScrollState()),
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
            sections.forEach { section ->
                MovieSection(
                    titleRes = section.titleRes,
                    contentType = section.contentType,
                    movies = section.movies,
                    onMovieClicked = { movie ->
                        viewModel.onEvent(
                            event = HomeUiEvent.MovieClicked(movie = movie)
                        )
                    },
                    onGetMoreClicked = section.onGetMoreClicked,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}