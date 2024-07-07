package com.yaroslavzghoba.model

import kotlinx.datetime.LocalDate

/**
 * The movie that the user has already watched
 */
class WatchedMovie(
    adult: Boolean,
    backdropPath: String?,
    genreIds: List<Int>,
    id: Int,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double?,
    posterPath: String?,
    releaseDate: LocalDate?,
    title: String,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Int?,
    val votePersonal: Double?,
    val databaseId: Int,  // Used instead of `id` in the db to keep the sequence
) : MovieCommon(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
