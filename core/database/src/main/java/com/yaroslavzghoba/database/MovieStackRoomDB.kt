package com.yaroslavzghoba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaroslavzghoba.Constants
import com.yaroslavzghoba.database.dao.GenreDao
import com.yaroslavzghoba.database.dao.WatchedMovieDao
import com.yaroslavzghoba.database.dao.WishedMovieDao
import com.yaroslavzghoba.database.model.GenreEntity
import com.yaroslavzghoba.database.model.WatchedMovieEntity
import com.yaroslavzghoba.database.model.WishedMovieEntity

/**
 * Application database
 */
@Database(
    entities = [
        GenreEntity::class,
        WatchedMovieEntity::class,
        WishedMovieEntity::class,
    ],
    version = 1,
)
internal abstract class MovieStackRoomDB : RoomDatabase() {

    abstract val genreDao: GenreDao
    abstract val watchedMovieDao: WatchedMovieDao
    abstract val wishedMovieDao: WishedMovieDao

    companion object {
        /**
         * Get implemented database as an instance of the [MovieStackRoomDB] class
         */
        fun getInstance(context: Context): MovieStackRoomDB {
            return Room.databaseBuilder(
                context = context,
                klass = MovieStackRoomDB::class.java,
                name = Constants.DATABASE_NAME,
            ).build()
        }
    }
}