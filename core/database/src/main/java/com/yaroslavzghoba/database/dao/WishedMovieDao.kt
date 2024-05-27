package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.MovieStackDatabase
import com.yaroslavzghoba.database.model.WishedMovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `wished_movies` table
 * defined in the [MovieStackDatabase]
 */
@Dao
interface WishedMovieDao {

    /**
     * Insert the [movie] or update if it already exists
     */
    @Upsert
    suspend fun upsertMovie(movie: WishedMovieEntity)

    /**
     * Insert each wished movie in the [movies]
     * or update if it already exists
     */
    @Upsert
    suspend fun upsertMovies(movies: List<WishedMovieEntity>)

    /**
     * Delete the [movie]
     */
    @Delete
    suspend fun deleteMovie(movie: WishedMovieEntity)

    /**
     * Delete each wished movie in the [movies]
     */
    @Delete
    suspend fun deleteMovies(movies: List<WishedMovieEntity>)

    /**
     * Find and return wished movie whose id equals [id]
     */
    @Query("SELECT * FROM wished_movies WHERE id = :id")
    fun getMovieById(id: Int): Flow<WishedMovieEntity>

    /**
     * Get all wished movies
     */
    @Query("SELECT * FROM wished_movies")
    fun getAllGenres(): Flow<List<WishedMovieEntity>>
}