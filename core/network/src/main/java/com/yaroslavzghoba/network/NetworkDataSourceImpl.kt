package com.yaroslavzghoba.network

import com.yaroslavzghoba.network.model.MoviesNetwork
import com.yaroslavzghoba.network.service.MovieService

class NetworkDataSourceImpl internal constructor(
    private val movieService: MovieService
) : NetworkDataSource {

    override suspend fun getMovies(language: String, page: Int): MoviesNetwork {
        return movieService.getMovies(language = language, page = page)
    }

    override suspend fun getMoviesByQuery(query: String, language: String): MoviesNetwork {
        return movieService.getMoviesByQuery(query = query, language = language)
    }
}