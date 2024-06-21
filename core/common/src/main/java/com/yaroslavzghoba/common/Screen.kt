package com.yaroslavzghoba.common

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class MovieList(val movieCategory: String) : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object WishList : Screen

    @Serializable
    data object Watched : Screen
}