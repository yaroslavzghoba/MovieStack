package com.yaroslavzghoba.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.WatchedMovieDbo
import kotlinx.coroutines.flow.Flow

/**
 * Provides access methods to the `watched_movies` table
 * defined in the [ApplicationDatabase]
 */
@Dao
interface WatchedMovieDao {

    /**Insert or update the [movie]*/
    @Upsert
    suspend fun upsert(movie: WatchedMovieDbo)

    /**Insert or update each movie in the [movies] list*/
    @Upsert
    suspend fun upsertAll(movies: List<WatchedMovieDbo>)

    /**Delete the [movie]*/
    @Delete
    suspend fun delete(movie: WatchedMovieDbo)

    /**Delete each movie in the [movies] list*/
    @Delete
    suspend fun deleteAll(movies: List<WatchedMovieDbo>)

    /**Delete all movies with where movie id is [id]*/
    @Query("DELETE FROM watched_movies WHERE id = :id")
    suspend fun deleteMoviesById(id: Int)

    /**Get all movies*/
    @Query("SELECT * FROM watched_movies")
    fun getAll(): Flow<List<WatchedMovieDbo>>

    /**Count the number of movies by movie id*/
    @Query("SELECT COUNT(*) FROM watched_movies WHERE id = :id")
    fun countMovies(id: Int): Flow<Int>
}