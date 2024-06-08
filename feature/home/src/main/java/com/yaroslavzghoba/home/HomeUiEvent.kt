package com.yaroslavzghoba.home

import com.yaroslavzghoba.model.Movie

sealed interface HomeUiEvent {

    data object BottomSheetDismissed : HomeUiEvent

    data class MovieClicked(val movie: Movie) : HomeUiEvent
}