package com.yaroslavzghoba.network.util

import com.yaroslavzghoba.model.util.Language

/**
 * Constant values for `network` module
 */
internal object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    val DEFAULT_LANGUAGE = Language.ENGLISH
    const val DEFAULT_PAGE = 1
    const val PAGE_SIZE = 20  // TMDB does not allow to change the page size
}