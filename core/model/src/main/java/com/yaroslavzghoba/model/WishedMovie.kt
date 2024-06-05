package com.yaroslavzghoba.model

/**
 * The movie the user wishes to watch
 */
data class WishedMovie(
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
    val scheduledViewingAt: String,
    val databaseId: Int,  // Used instead of `id` in the db to keep the sequence
)
