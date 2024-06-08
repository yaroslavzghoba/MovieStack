package com.yaroslavzghoba.movie_list

import com.yaroslavzghoba.model.Movie

sealed interface MovieListUiEvent {

    data object BottomSheetDismissed : MovieListUiEvent

    data class MovieClicked(val movie: Movie) : MovieListUiEvent
}