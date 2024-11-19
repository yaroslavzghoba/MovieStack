package com.yaroslavzghoba.network

import com.yaroslavzghoba.network.model.DiscoverDto
import com.yaroslavzghoba.network.model.GenresDto
import com.yaroslavzghoba.network.model.MovieDetailsDto
import com.yaroslavzghoba.network.model.NowPlayingDto
import com.yaroslavzghoba.network.model.PopularDto
import com.yaroslavzghoba.network.model.SearchedDto
import com.yaroslavzghoba.network.model.TopRatedDto
import com.yaroslavzghoba.network.model.UpcomingDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class NetworkDataSourceImpl internal constructor(
    private val httpClient: HttpClient,
) : NetworkDataSource {

    override suspend fun getAllGenres(language: String): GenresDto {
        return httpClient.get {
            url {
                path("genre/movie/list")
                parameters.append(name = "language", value = language)
            }
        }.body()
    }

    override suspend fun getMovies(
        language: String,
        page: Int,
    ): DiscoverDto = httpClient.get {
        url {
            path("discover/movie")
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
    ): NowPlayingDto = httpClient.get {
        url {
            path("movie/now_playing")
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
    ): PopularDto = httpClient.get {
        url {
            path("movie/popular")
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int,
    ): TopRatedDto = httpClient.get {
        url {
            path("movie/top_rated")
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int,
    ): UpcomingDto = httpClient.get {
        url {
            path("movie/upcoming")
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getMoviesByQuery(
        query: String,
        language: String,
        page: Int,
    ): SearchedDto = httpClient.get {
        url {
            path("search/movie")
            parameters.append(name = "query", value = query)
            parameters.append(name = "language", value = language)
            parameters.append(name = "page", value = page.toString())
        }
    }.body()

    override suspend fun getMovieDetails(
        id: Int,
        language: String,
    ): MovieDetailsDto = httpClient.get {
        url {
            path("movie/$id")
            parameters.append(name = "language", value = language)
        }
    }.body()
}