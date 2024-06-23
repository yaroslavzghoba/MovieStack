package com.yaroslavzghoba.movie_details

internal sealed interface MovieDetailsUiEvent {
    data object ViewingScheduling : MovieDetailsUiEvent
    data object AddMovieToWatched : MovieDetailsUiEvent
    data object AddMovieToWishList : MovieDetailsUiEvent
    data object RemoveMovieFromWatched : MovieDetailsUiEvent
    data object RemoveMovieFromWishList : MovieDetailsUiEvent
    data object MoveMovieToWatched : MovieDetailsUiEvent
    data object MoveMovieToWishList : MovieDetailsUiEvent
}