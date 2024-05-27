package com.yaroslavzghoba.database

import com.yaroslavzghoba.database.dao.GenreDao
import com.yaroslavzghoba.database.dao.WatchedMovieDao
import com.yaroslavzghoba.database.dao.WishedMovieDao

/**
 * Application database
 */
class MovieStackDatabase internal constructor(
    private val database: MovieStackRoomDB
) {
    val genreDao: GenreDao
        get() = database.genreDao

    val watchedMovieDao: WatchedMovieDao
        get() = database.watchedMovieDao

    val wishedMovieDao: WishedMovieDao
        get() = database.wishedMovieDao
}