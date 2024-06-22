package com.yaroslavzghoba.network.service

import com.yaroslavzghoba.core.network.BuildConfig
import com.yaroslavzghoba.network.model.DiscoverDto
import com.yaroslavzghoba.network.model.GenresDto
import com.yaroslavzghoba.network.model.MovieDetailsDto
import com.yaroslavzghoba.network.model.MovieDto
import com.yaroslavzghoba.network.model.NowPlayingDto
import com.yaroslavzghoba.network.model.PopularDto
import com.yaroslavzghoba.network.model.SearchedDto
import com.yaroslavzghoba.network.model.TopRatedDto
import com.yaroslavzghoba.network.model.UpcomingDto
import com.yaroslavzghoba.network.util.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Access token for TMDB API service.
 * Use in header instead of API key
 */
private const val ACCESS_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN

/**
 * Provides access to the remote movie API.
 * See API details [here](https://developer.themoviedb.org/docs/getting-started)
 */
internal interface MovieService {

    /**
     * Get a list of all genres
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     *
     * @return [GenresDto] that contains a list of [GenresDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("genre/movie/list")
    suspend fun getAllGenres(
        @Query("language") language: String,
    ): GenresDto

    /**
     * Get discover movies
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     *
     * @return [DiscoverDto] that contains a list of [MovieDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    // TODO: Add more filters for `discover/movie` endpoint
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): DiscoverDto

    /**
     * Get now playing movies
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     *
     * @return [NowPlayingDto] that contains a list of [MovieDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): NowPlayingDto

    /**
     * Get popular movies
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     *
     * @return [PopularDto] that contains a list of [MovieDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): PopularDto

    /**
     * Get top rated movies
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     *
     * @return [TopRatedDto] that contains a list of [MovieDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): TopRatedDto

    /**
     * Get upcoming movies
     *
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page
     *
     * @return [UpcomingDto] that contains a list of [MovieDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): UpcomingDto

    /**
     * Get a list of [MovieDto] by search [query]
     *
     * @param query describing the content the user wants to find
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): SearchedDto

    /**
     * Get details about movie by its [id]
     *
     * @param id of the movie you want to get details from.
     * @param language of the content. Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     *
     * @return [MovieDetailsDto]
     */
    // TODO: Create interceptor to replace `Headers` annotation
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("language") language: String,
    ): MovieDetailsDto
}