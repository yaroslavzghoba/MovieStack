package com.yaroslavzghoba.home.util

import androidx.annotation.StringRes
import androidx.paging.compose.LazyPagingItems
import com.yaroslavzghoba.model.Movie

internal data class MovieSectionItem(
    @StringRes val titleRes: Int,
    val contentType: String,
    val movies: LazyPagingItems<Movie>,
    val onGetMoreMovies: () -> Unit,
)
