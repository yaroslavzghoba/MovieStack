package com.yaroslavzghoba.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApplicationRepository,
) : ViewModel() {

    /**A list of discover movies that can be updated*/
    val discoverMovies = repository.getDiscoverMovies()
        .cachedIn(viewModelScope)

    /**A list of popular movies that can be updated*/
    val popularMovies = repository.getPopularMovies()
        .cachedIn(viewModelScope)

    /**A list of top rated movies that can be updated*/
    val topRatedMovies = repository.getTopRatedMovies()
        .cachedIn(viewModelScope)

    /**A list of now playing movies that can be updated*/
    val nowPlayingMovies = repository.getNowPlayingMovies()
        .cachedIn(viewModelScope)

    /**A list of upcoming movies that can be updated*/
    val upcomingMovies = repository.getUpcomingMovies()
        .cachedIn(viewModelScope)
}