package com.yaroslavzghoba.model

import kotlinx.datetime.LocalDate

/**
 * Common properties for all movies
 */
abstract class MovieCommon(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: LocalDate?,
    val title: String,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)