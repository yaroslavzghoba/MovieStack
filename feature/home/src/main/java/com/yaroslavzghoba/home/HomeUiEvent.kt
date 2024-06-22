package com.yaroslavzghoba.home

internal sealed interface HomeUiEvent {
    data class SearchMoviesRequest(val query: String) : HomeUiEvent
}