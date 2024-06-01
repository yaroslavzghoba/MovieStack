package com.yaroslavzghoba.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.UpcomingMovieDbo

/**
 * Provides access methods to the `upcoming_movies_cache` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface UpcomingMovieDao {

    /**Insert or update each movie in the [movies] list*/
    @Upsert
    suspend fun upsertAll(movies: List<UpcomingMovieDbo>)

    /**Delete all movies*/
    @Query("DELETE FROM upcoming_movies_cache")
    suspend fun deleteAll()

    /**Get all movies*/
    @Query("SELECT * FROM upcoming_movies_cache")
    fun pagingSource(): PagingSource<Int, UpcomingMovieDbo>
}