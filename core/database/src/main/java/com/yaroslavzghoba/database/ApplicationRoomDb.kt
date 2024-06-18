package com.yaroslavzghoba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaroslavzghoba.database.dao.DiscoverMovieDao
import com.yaroslavzghoba.database.dao.GenreDao
import com.yaroslavzghoba.database.dao.NowPlayingMovieDao
import com.yaroslavzghoba.database.dao.PopularMovieDao
import com.yaroslavzghoba.database.dao.SearchedMovieDao
import com.yaroslavzghoba.database.dao.TopRatedMovieDao
import com.yaroslavzghoba.database.dao.UpcomingMovieDao
import com.yaroslavzghoba.database.dao.WatchedMovieDao
import com.yaroslavzghoba.database.dao.WishedMovieDao
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.GenreDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.SearchedMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.database.model.WatchedMovieDbo
import com.yaroslavzghoba.database.model.WishedMovieDbo
import com.yaroslavzghoba.database.util.Constants

@Database(
    entities = [
        DiscoverMovieDbo::class,
        GenreDbo::class,
        NowPlayingMovieDbo::class,
        PopularMovieDbo::class,
        SearchedMovieDbo::class,
        TopRatedMovieDbo::class,
        UpcomingMovieDbo::class,
        WatchedMovieDbo::class,
        WishedMovieDbo::class,
    ],
    version = 1,
)
internal abstract class ApplicationRoomDb : RoomDatabase() {

    abstract val discoverMovieDao: DiscoverMovieDao
    abstract val genreDao: GenreDao
    abstract val nowPlayingMovieDao: NowPlayingMovieDao
    abstract val popularMovieDao: PopularMovieDao
    abstract val searchedMovieDao: SearchedMovieDao
    abstract val topRatedMovieDao: TopRatedMovieDao
    abstract val upcomingMovieDao: UpcomingMovieDao
    abstract val watchedMovieDao: WatchedMovieDao
    abstract val wishedMovieDao: WishedMovieDao

    companion object {
        /**
         * Get implemented database as an instance of the [ApplicationRoomDb] class
         */
        fun getInstance(context: Context): ApplicationRoomDb {
            return Room.databaseBuilder(
                context = context,
                klass = ApplicationRoomDb::class.java,
                name = Constants.DATABASE_NAME,
            ).build()
        }
    }
}