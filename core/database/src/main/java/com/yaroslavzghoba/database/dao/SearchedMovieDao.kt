package com.yaroslavzghoba.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.SearchedMovieDbo

/**
 * Provides access methods to the `searched_movies_cache` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface SearchedMovieDao {

    /**Insert or update each movie in the [movies] list*/
    @Upsert
    suspend fun upsertAll(movies: List<SearchedMovieDbo>)

    /**Delete all movies*/
    @Query("DELETE FROM searched_movies_cache")
    suspend fun deleteAll()

    /**Get all movies*/
    @Query("SELECT * FROM searched_movies_cache")
    fun pagingSource(): PagingSource<Int, SearchedMovieDbo>
}