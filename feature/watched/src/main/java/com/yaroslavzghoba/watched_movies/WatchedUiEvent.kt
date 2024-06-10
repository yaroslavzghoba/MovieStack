package com.yaroslavzghoba.watched_movies

import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie

internal sealed interface WatchedUiEvent {
    data object BottomSheetDismissed : WatchedUiEvent
    data class MovieDetails(val movie: WatchedMovie) : WatchedUiEvent
    data class MoveMovieToWished(val movie: WatchedMovie) : WatchedUiEvent
    data class DeleteMovie(val movie: WatchedMovie) : WatchedUiEvent
}