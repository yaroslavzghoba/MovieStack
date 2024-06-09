package com.yaroslavzghoba.wish_list

import com.yaroslavzghoba.model.WishedMovie

internal sealed interface WishListUiEvent {
    data object BottomSheetDismissed : WishListUiEvent
    data class MovieDetails(val movie: WishedMovie) : WishListUiEvent
    data class MoveMovieToWatched(val movie: WishedMovie) : WishListUiEvent
    data class DeleteMovie(val movie: WishedMovie) : WishListUiEvent
}