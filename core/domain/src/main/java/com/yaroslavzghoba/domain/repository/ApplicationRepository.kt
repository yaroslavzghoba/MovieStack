package com.yaroslavzghoba.domain.repository

import androidx.paging.PagingData
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.MovieDetails
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


    // ==========WATCHED MOVIES==========
    /**Insert or update the watched [movie]*/
    suspend fun upsertWatchedMovie(movie: WatchedMovie)

    /**Insert or update each watched movie in the [movies] list*/
    suspend fun upsertWatchedMovies(movies: List<WatchedMovie>)

    /**Add [movie] to the watched movies*/
    suspend fun addMovieToWatched(movie: MovieDetails)

    /**Remove the [movie] from the watched and insert it to into the wished*/
    suspend fun moveWatchedMovieToWished(movie: MovieDetails)

    /**Delete the watched [movie]*/
    suspend fun deleteWatchedMovie(movie: WatchedMovie)

    /**
     * Delete the watched movie by its movie id.
     * If the table contains several entries with the same id it'll delete theme all!
     */
    suspend fun deleteWatchedMoviesById(id: Int)

    /**Delete each watched movie in the [movies] list*/
    suspend fun deleteWatchedMovies(movies: List<WatchedMovie>)

    /**Get all watched movies*/
    fun getAllWatchedMovies(): Flow<List<WatchedMovie>>

    /**Count the number of watched movies by movie id*/
    fun countWatchedMoviesById(id: Int): Flow<Int>


    // ==========WISHED MOVIES==========
    /**Insert or update the wished [movie]*/
    suspend fun upsertWishedMovie(movie: WishedMovie)

    /**Insert or update each wished movie in the [movies] list*/
    suspend fun upsertWishedMovies(movies: List<WishedMovie>)

    /**Add [movie] to the wished movies*/
    suspend fun addMovieToWished(movie: MovieDetails)

    /**Remove the [movie] from the wished and insert it to into the watched*/
    suspend fun moveWishedMovieToWatched(movie: MovieDetails)

    /**Delete the wished [movie]*/
    suspend fun deleteWishedMovie(movie: WishedMovie)

    /**Delete each wished movie in the [movies] list*/
    suspend fun deleteWishedMovies(movies: List<WishedMovie>)

    /**
     * Delete the wished movie by its movie id.
     * If the table contains several entries with the same id it'll delete theme all!
     */
    suspend fun deleteWishedMoviesById(id: Int)

    /**Get all movies*/
    fun getAllWishedMovies(): Flow<List<WishedMovie>>

    /**Count the number of wished movies by movie id*/
    fun countWishedMoviesById(id: Int): Flow<Int>


    // ==========NETWORK==========
    /**Delete exist and save new genres*/
    suspend fun updateGenres()

    /**Get all genres*/
    fun getAllGenres(): Flow<List<Genre>>

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

    /**Get movie details by its id*/
    suspend fun getMovieDetails(id: Int): Flow<Result<MovieDetails>>
}