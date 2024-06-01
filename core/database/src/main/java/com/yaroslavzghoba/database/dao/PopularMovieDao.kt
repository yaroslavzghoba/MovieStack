package com.yaroslavzghoba.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.PopularMovieDbo

/**
 * Provides access methods to the `popular_movies_cache` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface PopularMovieDao {

    /**Insert or update each movie in the [movies] list*/
    @Upsert
    suspend fun upsertAll(movies: List<PopularMovieDbo>)

    /**Delete all movies*/
    @Query("DELETE FROM popular_movies_cache")
    suspend fun deleteAll()

    /**Get all movies*/
    @Query("SELECT * FROM popular_movies_cache")
    fun pagingSource(): PagingSource<Int, PopularMovieDbo>
}