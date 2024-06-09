package com.yaroslavzghoba.movie_list

import com.yaroslavzghoba.model.Movie

internal sealed interface MovieListUiEvent {
    data object BottomSheetDismissed : MovieListUiEvent
    data class MovieDetails(val movie: Movie) : MovieListUiEvent
    data class MoveMovieToWished(val movie: Movie) : MovieListUiEvent
    data class MoveMovieToWatched(val movie: Movie) : MovieListUiEvent
}