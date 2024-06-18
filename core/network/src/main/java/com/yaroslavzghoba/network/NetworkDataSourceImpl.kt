package com.yaroslavzghoba.network

import com.yaroslavzghoba.network.model.DiscoverDto
import com.yaroslavzghoba.network.model.GenresDto
import com.yaroslavzghoba.network.model.NowPlayingDto
import com.yaroslavzghoba.network.model.PopularDto
import com.yaroslavzghoba.network.model.SearchedDto
import com.yaroslavzghoba.network.model.TopRatedDto
import com.yaroslavzghoba.network.model.UpcomingDto
import com.yaroslavzghoba.network.service.MovieService

class NetworkDataSourceImpl internal constructor(
    private val movieService: MovieService
) : NetworkDataSource {

    override suspend fun getAllGenres(language: String): GenresDto {
        return movieService.getAllGenres(language = language)
    }

    override suspend fun getMovies(
        language: String,
        page: Int,
    ): DiscoverDto = movieService.getMovies(
        language = language,
        page = page
    )

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
    ): NowPlayingDto = movieService.getNowPlayingMovies(
        language = language,
        page = page
    )

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
    ): PopularDto = movieService.getPopularMovies(
        language = language,
        page = page,
    )

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int,
    ): TopRatedDto = movieService.getTopRatedMovies(
        language = language,
        page = page,
    )

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int,
    ): UpcomingDto = movieService.getUpcomingMovies(
        language = language,
        page = page,
    )

    override suspend fun getMoviesByQuery(
        query: String,
        language: String,
        page: Int,
    ): SearchedDto = movieService.getMoviesByQuery(
        query = query,
        language = language,
        page = page,
    )
}