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
    val discoverMovies = viewModel.discoverMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

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
            MovieSection(
                titleRes = R.string.home_popular_section_title,
                contentType = "Popular movies",
                movies = popularMovies,
                onGetMore = onGetMorePopular,
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                titleRes = R.string.home_top_rated_section_title,
                contentType = "Top rated movies",
                movies = topRatedMovies,
                onGetMore = onGetMoreTopRated,
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                titleRes = R.string.home_now_playing_section_title,
                contentType = "Now playing movies",
                movies = nowPlayingMovies,
                onGetMore = onGetMoreNowPlaying,
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                titleRes = R.string.home_upcoming_section_title,
                contentType = "Upcoming movies",
                movies = upcomingMovies,
                onGetMore = onGetMoreUpcoming,
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                titleRes = R.string.home_discover_section_title,
                contentType = "Discover movies",
                movies = discoverMovies,
                onGetMore = onGetMoreDiscover,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}