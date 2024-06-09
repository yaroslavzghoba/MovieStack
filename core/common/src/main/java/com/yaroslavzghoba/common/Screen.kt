package com.yaroslavzghoba.common

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class MovieList(val movieCategory: String) : Screen

    @Serializable
    data object WishList : Screen
}