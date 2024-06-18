package com.yaroslavzghoba.home

import com.yaroslavzghoba.model.Movie

internal sealed interface HomeUiEvent {
    data class SearchMoviesRequest(val query: String) : HomeUiEvent
    data object BottomSheetDismissed : HomeUiEvent
    data class MovieDetails(val movie: Movie) : HomeUiEvent
    data class MoveMovieToWished(val movie: Movie) : HomeUiEvent
    data class MoveMovieToWatched(val movie: Movie) : HomeUiEvent
}