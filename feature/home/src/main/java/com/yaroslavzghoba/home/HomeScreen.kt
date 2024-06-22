package com.yaroslavzghoba.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.yaroslavzghoba.common.animationSpecDefault
import com.yaroslavzghoba.home.component.MovieSection
import com.yaroslavzghoba.home.component.SearchContent
import com.yaroslavzghoba.home.util.MovieSectionItem
import com.yaroslavzghoba.ui.SearchLayout
import com.yaroslavzghoba.ui.rememberSearchLayoutState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    isNavigationBarVisible: (Boolean) -> Unit,
    onGetMovieDetails: (Int) -> Unit,
    onGetMoreDiscover: () -> Unit,
    onGetMoreNowPlaying: () -> Unit,
    onGetMorePopular: () -> Unit,
    onGetMoreTopRated: () -> Unit,
    onGetMoreUpcoming: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movieSections = listOf(
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

    val focusManager = LocalFocusManager.current
    val searchLayoutState = rememberSearchLayoutState()
    val searchBarPaddings by animateDpAsState(
        targetValue = if (searchLayoutState.expanded) 0.dp else 16.dp,
        animationSpec = animationSpecDefault(),
        label = "Search layout external paddings",
    )

    // Hide navigation bar if the search layout is expanded
    isNavigationBarVisible(!searchLayoutState.expanded)

    // Search every time when the search query was changed
    LaunchedEffect(searchLayoutState.query) {
        val event = HomeUiEvent.SearchMoviesRequest(query = searchLayoutState.query)
        viewModel.onEvent(event = event)
    }

    // Exit from the search mode using back button
    if (searchLayoutState.expanded) {
        BackHandler {
            searchLayoutState.collapse()
            focusManager.clearFocus()
        }
    }


    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface),
    ) {
        SearchLayout(
            searchLayoutState = searchLayoutState,
            onActionButtonClicked = {
                if (searchLayoutState.expanded) {
                    focusManager.clearFocus()
                    searchLayoutState.collapse()
                } else {
                    // TODO: Make focus request to focus user on the input field
                    searchLayoutState.expand()
                }
            },
            modifier = Modifier
                .padding(searchBarPaddings)
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) searchLayoutState.expand()
                }
        ) {
            SearchContent(
                movies = viewModel.searchedMovies.collectAsLazyPagingItems(),
                onGetMovieDetails = onGetMovieDetails,
            )
        }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            movieSections.forEach { movieSection ->
                MovieSection(
                    titleRes = movieSection.titleRes,
                    contentType = movieSection.contentType,
                    movies = movieSection.movies,
                    onGetMovieDetails = onGetMovieDetails,
                    onGetMoreMovies = movieSection.onGetMoreMovies,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}