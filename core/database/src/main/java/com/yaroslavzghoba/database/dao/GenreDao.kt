package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.MovieStackDatabase
import com.yaroslavzghoba.database.model.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `genres` table
 * defined in the [MovieStackDatabase]
 */
@Dao
interface GenreDao {

    /**
     * Insert each genre in the [genres]
     * or update if it already exists
     */
    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    /**
     * Delete each genre in the [genres]
     */
    @Delete
    suspend fun deleteGenres(genres: List<GenreEntity>)

    /**
     * Find and return a genre whose id equals [id]
     */
    @Query("SELECT * FROM genres WHERE id = :id")
    fun getGenreById(id: Int): Flow<GenreEntity>

    /**
     * Get all genres
     */
    @Query("SELECT * FROM genres")
    fun getAllGenres(): Flow<List<GenreEntity>>
}