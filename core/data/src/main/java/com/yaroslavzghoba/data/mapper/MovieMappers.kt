package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.model.Movie
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
)

/**
 * Convert [MovieDto] to [Movie]
 */
internal fun MovieDto.toMovie() = Movie(
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
)