package com.yaroslavzghoba.model

/**
 * The movie suggested to the user
 */
class Movie(
    adult: Boolean,
    backdropPath: String?,
    genreIds: List<Int>,
    id: Int,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double,
    posterPath: String?,
    releaseDate: String,
    title: String,
    video: Boolean,
    voteAverage: Double,
    voteCount: Int,
    val cacheId: Int,  // Used instead of `id`, to preserve the same sequence as when loading
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
