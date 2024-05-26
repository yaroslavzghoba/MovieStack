package com.yaroslavzghoba.network.service

import com.yaroslavzghoba.core.network.BuildConfig
import com.yaroslavzghoba.network.model.MovieNetwork
import com.yaroslavzghoba.network.model.MoviesNetwork
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Access token for TMDB API service.
 * Use in header instead of API key
 */
private const val ACCESS_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN

/**
 * Provides access to the remote API with movies
 */
internal interface MovieService {

    /**
     * Get a list of [MovieNetwork]
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     */
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MoviesNetwork

    /**
     * Get a list of [MovieNetwork] by search [query]
     *
     * @param query describing the content the user wants to find
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     */
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("query") query: String,
        @Query("language") language: String,
    ): MoviesNetwork
}