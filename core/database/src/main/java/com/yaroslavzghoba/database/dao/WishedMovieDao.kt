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

    /**Delete all movies with where movie id is [id]*/
    @Query("DELETE FROM wished_movies WHERE id = :id")
    suspend fun deleteMoviesById(id: Int)

    /**Get all movies*/
    @Query("SELECT * FROM wished_movies")
    fun getAll(): Flow<List<WishedMovieDbo>>

    /**Count the number of movies by movie id*/
    @Query("SELECT COUNT(*) FROM wished_movies WHERE id = :id")
    fun countMovies(id: Int): Flow<Int>
}