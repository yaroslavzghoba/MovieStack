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
    private val repository: ApplicationRepository,
) : ViewModel() {

    private val navState: Screen.MovieList = savedStateHandle.toRoute()

    private val movieCategory: MovieCategory = navState.movieCategory.toMovieCategory()

    @StringRes
    val titleRes = when (movieCategory) {
        MovieCategory.DISCOVER -> R.string.movie_list_discover_title
        MovieCategory.POPULAR -> R.string.movie_list_popular_title
        MovieCategory.NOW_PLAYING -> R.string.movie_list_now_playing_title
        MovieCategory.TOP_RATED -> R.string.movie_list_top_rated_title
        MovieCategory.UPCOMING -> R.string.movie_list_upcoming_title
    }

    val contentType = movieCategory.name

    val movies = with(repository) {
        when (movieCategory) {
            MovieCategory.DISCOVER -> getDiscoverMovies()
            MovieCategory.POPULAR -> getPopularMovies()
            MovieCategory.NOW_PLAYING -> getNowPlayingMovies()
            MovieCategory.TOP_RATED -> getTopRatedMovies()
            MovieCategory.UPCOMING -> getUpcomingMovies()
        }.cachedIn(viewModelScope)
    }
}