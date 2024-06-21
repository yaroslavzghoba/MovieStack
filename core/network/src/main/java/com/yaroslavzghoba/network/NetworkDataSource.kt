package com.yaroslavzghoba.network

import com.yaroslavzghoba.network.model.DiscoverDto
import com.yaroslavzghoba.network.model.GenresDto
import com.yaroslavzghoba.network.model.MovieDto
import com.yaroslavzghoba.network.model.NowPlayingDto
import com.yaroslavzghoba.network.model.PopularDto
import com.yaroslavzghoba.network.model.SearchedDto
import com.yaroslavzghoba.network.model.TopRatedDto
import com.yaroslavzghoba.network.model.UpcomingDto
import com.yaroslavzghoba.network.util.Constants

/**
 * Application network data source
 */
interface NetworkDataSource {

    /**
     * The maximum number of values that the API can return in single response.
     * The value is a constant for the API and cannot be changed
     */
    val PAGE_SIZE: Int
        get() = Constants.PAGE_SIZE

    /**
     * Get a list of all genres
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     *
     * @return [GenresDto] that contains a list of [GenresDto]
     */
    suspend fun getAllGenres(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
    ): GenresDto

    /**
     * Get discover movies
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [DiscoverDto] that contains a list of [MovieDto]
     */
    // TODO: Add more filters for `getMovies` endpoint
    suspend fun getMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): DiscoverDto

    /**
     * Get now playing movies
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [NowPlayingDto] that contains a list of [MovieDto]
     */
    suspend fun getNowPlayingMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): NowPlayingDto

    /**
     * Get popular movies
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [PopularDto] that contains a list of [MovieDto]
     */
    suspend fun getPopularMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): PopularDto

    /**
     * Get top rated movies
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [TopRatedDto] that contains a list of [MovieDto]
     */
    suspend fun getTopRatedMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): TopRatedDto

    /**
     * Get upcoming movies
     *
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE].
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [UpcomingDto] that contains a list of [MovieDto]
     */
    suspend fun getUpcomingMovies(
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): UpcomingDto

    /**
     * Get movies by search [query]
     *
     * @param query describing the content the user wants to find
     * @param language of the content. By default it's ISO 369-1 of [Constants.DEFAULT_LANGUAGE]
     * Accepts two-letter ISO 369-1 language code. See more about
     * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
     * @param page number of the current page. By default it's [Constants.DEFAULT_PAGE]
     *
     * @return [DiscoverDto] that contains a list of [MovieDto]
     */
    suspend fun getMoviesByQuery(
        query: String,
        language: String = Constants.DEFAULT_LANGUAGE.iso369,
        page: Int = Constants.DEFAULT_PAGE,
    ): SearchedDto
}