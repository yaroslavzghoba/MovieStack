package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.MovieStackDatabase
import com.yaroslavzghoba.database.model.WatchedMovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `watched_movies` table
 * defined in the [MovieStackDatabase]
 */
@Dao
interface WatchedMovieDao {

    /**
     * Insert the [movie] or update if it already exists
     */
    @Upsert
    suspend fun upsertMovie(movie: WatchedMovieEntity)

    /**
     * Insert each watched movie in the [movies]
     * or update if it already exists
     */
    @Upsert
    suspend fun upsertMovies(movies: List<WatchedMovieEntity>)

    /**
     * Delete the [movie]
     */
    @Delete
    suspend fun deleteMovie(movie: WatchedMovieEntity)

    /**
     * Delete each watched movie in the [movies]
     */
    @Delete
    suspend fun deleteMovies(movies: List<WatchedMovieEntity>)

    /**
     * Find and return watched movie whose id equals [id]
     */
    @Query("SELECT * FROM watched_movies WHERE id = :id")
    fun getMovieById(id: Int): Flow<WatchedMovieEntity>

    /**
     * Get all watched movies
     */
    @Query("SELECT * FROM watched_movies")
    fun getAllGenres(): Flow<List<WatchedMovieEntity>>
}