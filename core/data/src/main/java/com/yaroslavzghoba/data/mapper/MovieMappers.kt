package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.SearchedMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import com.yaroslavzghoba.network.model.MovieDto

/**
 * Convert [DiscoverMovieDbo] to [Movie]
 */
internal fun DiscoverMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [NowPlayingMovieDbo] to [Movie]
 */
internal fun NowPlayingMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [PopularMovieDbo] to [Movie]
 */
internal fun PopularMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [PopularMovieDbo] to [Movie]
 */
internal fun SearchedMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [TopRatedMovieDbo] to [Movie]
 */
internal fun TopRatedMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [UpcomingMovieDbo] to [Movie]
 */
internal fun UpcomingMovieDbo.toMovie() = Movie(
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
    cacheId = cacheId,
)

/**
 * Convert [MovieDto] to [Movie]
 */
internal fun MovieDto.toMovie(cacheId: Int) = Movie(
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
    cacheId = cacheId
)

/**
 * Convert [Movie] to [DiscoverMovieDbo]
 */
internal fun Movie.toDiscoverMovieDbo() = DiscoverMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [NowPlayingMovieDbo]
 */
internal fun Movie.toNowPlayingMovieDbo() = NowPlayingMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [PopularMovieDbo]
 */
internal fun Movie.toPopularMovieDbo() = PopularMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [SearchedMovieDbo]
 */
internal fun Movie.toSearchedMovieDbo() = SearchedMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [TopRatedMovieDbo]
 */
internal fun Movie.toTopRatedMovieDbo() = TopRatedMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [UpcomingMovieDbo]
 */
internal fun Movie.toUpcomingMovieDbo() = UpcomingMovieDbo(
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
    cacheId = cacheId,
)

/**
 * Convert [Movie] to [WatchedMovie]
 */
internal fun Movie.toWatchedMovie(
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

/**
 * Convert [Movie] to [WishedMovie]
 */
internal fun Movie.toWishedMovie(
    scheduledViewingAt: String?,
    databaseId: Int,
): WishedMovie = WishedMovie(
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
    scheduledViewingAt = scheduledViewingAt,
    databaseId = databaseId,
)