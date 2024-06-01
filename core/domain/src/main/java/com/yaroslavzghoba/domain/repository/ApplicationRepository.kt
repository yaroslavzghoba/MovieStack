package com.yaroslavzghoba.domain.repository

import androidx.paging.PagingData
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import kotlinx.coroutines.flow.Flow

/**
 * Repository that binds all application data sources.
 */
interface ApplicationRepository {

    /**Delete exist and save new genres*/
    suspend fun updateGenres()

    /**Get all genres*/
    fun getAllGenres(): Flow<List<Genre>>



    /**Insert or update the watched [movie]*/
    suspend fun upsertWatchedMovie(movie: WatchedMovie)

    /**Insert or update each watched movie in the [movies] list*/
    suspend fun upsertWatchedMovies(movies: List<WatchedMovie>)

    /**Delete the watched [movie]*/
    suspend fun deleteWatchedMovie(movie: WatchedMovie)

    /**Delete each watched movie in the [movies] list*/
    suspend fun deleteWatchedMovies(movies: List<WatchedMovie>)

    /**Find and return watched movie whose id equals [id]*/
    fun getWatchedMovieById(id: Int): Flow<WatchedMovie>

    /**Get all watched movies*/
    fun getAllWatchedMovies(): Flow<List<WatchedMovie>>



    /**Insert or update the wished [movie]*/
    suspend fun upsertWishedMovie(movie: WishedMovie)

    /**Insert or update each wished movie in the [movies] list*/
    suspend fun upsertWishedMovies(movies: List<WishedMovie>)

    /**Delete the wished [movie]*/
    suspend fun deleteWishedMovie(movie: WishedMovie)

    /**Delete each wished movie in the [movies] list*/
    suspend fun deleteWishedMovies(movies: List<WishedMovie>)

    /**Find and return wished movie whose id equals [id]*/
    fun getWishedMovieById(id: Int): Flow<WishedMovie>

    /**Get all movies*/
    fun getAllWishedMovies(): Flow<List<WishedMovie>>



    /**Clear discover movies cache*/
    suspend fun clearDiscoverMoviesCache()

    /**Get discover movies cache*/
    fun getDiscoverMovies(): Flow<PagingData<Movie>>

    /**Clear now playing movies cache*/
    suspend fun clearNowPlayingMoviesCache()

    /**Get now playing movies cache*/
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>

    /**Clear popular movies cache*/
    suspend fun clearPopularMoviesCache()

    /**Get popular movies cache*/
    fun getPopularMovies(): Flow<PagingData<Movie>>

    /**Clear top rated movies cache*/
    suspend fun clearTopRatedMoviesCache()

    /**Get top rated movies cache*/
    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    /**Clear discover movies cache*/
    suspend fun clearUpcomingMoviesCache()

    /**Get upcoming movies cache*/
    fun getUpcomingMovies(): Flow<PagingData<Movie>>

    /**Get movies by search [query]*/
    fun getMoviesByQuery(query: String): Flow<Result<List<Movie>>>
}