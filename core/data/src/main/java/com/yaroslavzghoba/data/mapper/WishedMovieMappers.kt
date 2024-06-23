package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.database.model.WishedMovieDbo
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import kotlinx.datetime.LocalDate

/**
 * Convert [WishedMovieDbo] to [WishedMovie]
 */
internal fun WishedMovieDbo.toWishedMovie() = WishedMovie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds.toIntList(),
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = LocalDate.parse(releaseDate),
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    scheduledViewingAt = scheduledViewingAt,
    databaseId = databaseId,
)

/**
 * Convert [WishedMovie] to [WishedMovieDbo]
 */
internal fun WishedMovie.toDbo() = WishedMovieDbo(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds.toStringList(),
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate.toString(),
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    scheduledViewingAt = scheduledViewingAt,
    databaseId = databaseId,
)

/**
 * Convert [WishedMovie] to [WatchedMovie]
 */
internal fun WishedMovie.toWatchedMovie(
    votePersonal: Double?,
    databaseId: Int,
): WatchedMovie = WatchedMovie(
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
    votePersonal = votePersonal,
    databaseId = databaseId,
)