package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.GenreDbo
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `genres` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface GenreDao {

    /**Insert or update each genre in the [genres] list*/
    @Upsert
    suspend fun upsertAll(genres: List<GenreDbo>)

    /**Delete each genre in the [genres] list*/
    @Delete
    suspend fun deleteAll(genres: List<GenreDbo>)

    /**Delete all genres*/
    @Query("DELETE FROM genres")
    suspend fun deleteAll()

    /**Get all genres*/
    @Query("SELECT * FROM genres")
    fun getAll(): Flow<List<GenreDbo>>
}