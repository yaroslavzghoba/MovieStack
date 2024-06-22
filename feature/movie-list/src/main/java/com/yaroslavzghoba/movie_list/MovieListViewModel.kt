package com.yaroslavzghoba.movie_list

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.yaroslavzghoba.common.MovieCategory
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.common.toMovieCategory
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: ApplicationRepository,
) : ViewModel() {

    private val route: Screen.MovieList = savedStateHandle.toRoute()
    private val movieCategory: MovieCategory = route.movieCategory.toMovieCategory()
    internal val contentType = movieCategory.name
    @StringRes
    internal val titleRes = when (movieCategory) {
        MovieCategory.DISCOVER -> R.string.discover_screen_title
        MovieCategory.POPULAR -> R.string.popular_screen_title
        MovieCategory.NOW_PLAYING -> R.string.now_playing_screen_title
        MovieCategory.TOP_RATED -> R.string.top_rated_screen_title
        MovieCategory.UPCOMING -> R.string.upcoming_screen_title
    }
    internal val movies = with(repository) {
        when (movieCategory) {
            MovieCategory.DISCOVER -> getDiscoverMovies()
            MovieCategory.POPULAR -> getPopularMovies()
            MovieCategory.NOW_PLAYING -> getNowPlayingMovies()
            MovieCategory.TOP_RATED -> getTopRatedMovies()
            MovieCategory.UPCOMING -> getUpcomingMovies()
        }.cachedIn(viewModelScope)
    }
}