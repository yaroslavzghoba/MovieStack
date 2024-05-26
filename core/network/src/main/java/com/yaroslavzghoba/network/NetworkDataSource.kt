package com.yaroslavzghoba.network

import com.yaroslavzghoba.network.model.MovieNetwork
import com.yaroslavzghoba.network.model.MoviesNetwork

/**
 * Application network data source
 */
interface NetworkDataSource {

    /**
     * Get a list of [MovieNetwork]
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     */
    suspend fun getMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369Code1,
        page: Int = Constants.DEFAULT_PAGE,
    ): MoviesNetwork

    /**
     * Get a list of [MovieNetwork] by search [query]
     *
     * @param query describing the content the user wants to find
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE]
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     */
    suspend fun getMoviesByQuery(
        query: String,
        language: String = Constants.DEFAULT_LANGUAGE.iso369Code1,
    ): MoviesNetwork
}