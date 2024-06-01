package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.WishedMovieDbo
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `wished_movies` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface WishedMovieDao {

    /**Insert or update the [movie]*/
    @Upsert
    suspend fun upsert(movie: WishedMovieDbo)

    /**Insert or update each movie in the [movies] list*/
    @Upsert
    suspend fun upsertAll(movies: List<WishedMovieDbo>)

    /**Delete the [movie]*/
    @Delete
    suspend fun delete(movie: WishedMovieDbo)

    /**Delete each movie in the [movies] list*/
    @Delete
    suspend fun deleteAll(movies: List<WishedMovieDbo>)

    /**Find and return movie whose id equals [id]*/
    @Query("SELECT * FROM wished_movies WHERE id = :id")
    fun getById(id: Int): Flow<WishedMovieDbo>

    /**Get all movies*/
    @Query("SELECT * FROM wished_movies")
    fun getAll(): Flow<List<WishedMovieDbo>>
}