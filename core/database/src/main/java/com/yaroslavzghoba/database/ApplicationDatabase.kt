package com.yaroslavzghoba.database

import android.content.Context
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
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.database.model.WatchedMovieDbo
import com.yaroslavzghoba.database.model.WishedMovieDbo

/**
 * Application database
 */
class ApplicationDatabase internal constructor(
    private val database: ApplicationRoomDb
) {
    /**Provide access to [DiscoverMovieDbo]*/
    val discoverMovieDao: DiscoverMovieDao
        get() = database.discoverMovieDao

    /**Provide access to [GenreDbo]*/
    val genreDao: GenreDao
        get() = database.genreDao

    /**Provide access to [NowPlayingMovieDbo]*/
    val nowPlayingMovieDao: NowPlayingMovieDao
        get() = database.nowPlayingMovieDao

    /**Provide access to [PopularMovieDbo]*/
    val popularMovieDao: PopularMovieDao
        get() = database.popularMovieDao

    /**Provide access to [PopularMovieDao]*/
    val searchedMovieDao: SearchedMovieDao
        get() = database.searchedMovieDao

    /**Provide access to [TopRatedMovieDbo]*/
    val topRatedMovieDao: TopRatedMovieDao
        get() = database.topRatedMovieDao

    /**Provide access to [UpcomingMovieDbo]*/
    val upcomingMovieDao: UpcomingMovieDao
        get() = database.upcomingMovieDao

    /**Provide access to [WatchedMovieDbo]*/
    val watchedMovieDao: WatchedMovieDao
        get() = database.watchedMovieDao

    /**Provide access to [WishedMovieDbo]*/
    val wishedMovieDao: WishedMovieDao
        get() = database.wishedMovieDao

    companion object {
        fun build(context: Context): ApplicationDatabase {
            return ApplicationDatabase(
                database = ApplicationRoomDb.getInstance(context = context)
            )
        }
    }
}