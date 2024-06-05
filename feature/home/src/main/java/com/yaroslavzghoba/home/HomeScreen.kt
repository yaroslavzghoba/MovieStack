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

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val discoverMovies = viewModel.discoverMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier,
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MovieSection(
                title = "Popular movies",
                movies = popularMovies,
                onGetMore = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                title = "Top rated movies",
                movies = topRatedMovies,
                onGetMore = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                title = "Now playing movies",
                movies = nowPlayingMovies,
                onGetMore = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                title = "Upcoming movies",
                movies = upcomingMovies,
                onGetMore = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
            MovieSection(
                title = "Discover movies",
                movies = discoverMovies,
                onGetMore = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}