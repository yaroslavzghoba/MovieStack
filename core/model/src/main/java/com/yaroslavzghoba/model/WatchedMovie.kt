package com.yaroslavzghoba.model

/**
 * The movie that the user has already watched
 */
data class WatchedMovie(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val votePersonal: Double?,
    val databaseId: Int,  // Used instead of `id` in the db to keep the sequence
)
