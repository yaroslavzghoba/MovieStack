package com.yaroslavzghoba.domain.repository

import androidx.paging.PagingData
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.UserPreferences
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import kotlinx.coroutines.flow.Flow

/**
 * Repository that binds all application data sources.
 */
interface ApplicationRepository {

    /**Get user preferences*/
    fun getUserPreferences(): Flow<UserPreferences>

    /**Update user preferences*/
    suspend fun updateUserPreferences(userPreferences: UserPreferences)


    /**Delete exist and save new genres*/
    suspend fun updateGenres()

    /**Get all genres*/
    fun getAllGenres(): Flow<List<Genre>>


    /**Insert or update the [movie] into watched movies*/
    suspend fun moveMovieToWatchedMovies(movie: Movie)

    /**Insert or update each movie in the [movies] list into watched movies*/
    suspend fun moveMoviesToWatchedMovies(movies: List<Movie>)

    /**
     * Delete the [movie] from wished movies
     * and insert or update it into watched movies
     */
    suspend fun moveWishedMovieToWatchedMovies(movie: WishedMovie)

    /**
     * Delete each movie in the [movies] list from wished movies
     * and insert or update them into watched movies
     */
    suspend fun moveWishedMoviesToWatchedMovies(movies: List<WishedMovie>)

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


    /**Insert or update the [movie] into wished movies*/
    suspend fun moveMovieToWishedMovies(movie: Movie)

    /**Insert or update each movie in the [movies] list into wished movies*/
    suspend fun moveMoviesToWishedMovies(movies: List<Movie>)

    /**
     * Delete the [movie] from watched movies
     * and insert or update it into wished movies
     */
    suspend fun moveWatchedMovieToWishedMovies(movie: WatchedMovie)

    /**
     * Delete each movie in the [movies] list from watched movies
     * and insert or update them into wished movies
     */
    suspend fun moveWatchedMoviesToWishedMovies(movies: List<WatchedMovie>)

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

    /**Clear searched movies cache*/
    suspend fun clearSearchedMoviesCache()

    /**Get movies by search [query]*/
    fun getMoviesByQuery(query: String): Flow<PagingData<Movie>>
}