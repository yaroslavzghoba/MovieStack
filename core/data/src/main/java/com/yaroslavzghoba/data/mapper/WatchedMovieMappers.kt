package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.database.model.WatchedMovieDbo
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie

/**
 * Convert [WatchedMovieDbo] to [WatchedMovie]
 */
internal fun WatchedMovieDbo.toWatchedMovie() = WatchedMovie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds.toIntList(),
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
    votePersonal = votePersonal,
    databaseId = databaseId,
)

/**
 * Convert [WishedMovie] to [WatchedMovieDbo]
 */
internal fun WatchedMovie.toDbo() = WatchedMovieDbo(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds.toStringList(),
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
    votePersonal = votePersonal,
    databaseId = databaseId,
)