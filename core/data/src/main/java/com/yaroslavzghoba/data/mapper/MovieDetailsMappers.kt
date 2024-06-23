package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.MovieCollection
import com.yaroslavzghoba.model.MovieDetails
import com.yaroslavzghoba.model.ProductionCompany
import com.yaroslavzghoba.model.ProductionCountry
import com.yaroslavzghoba.model.SpokenLanguage
import com.yaroslavzghoba.network.model.MovieCollectionDto
import com.yaroslavzghoba.network.model.MovieDetailsDto
import com.yaroslavzghoba.network.model.ProductionCompanyDto
import com.yaroslavzghoba.network.model.ProductionCountryDto
import com.yaroslavzghoba.network.model.SpokenLanguageDto
import kotlinx.datetime.LocalDate

/**
 * Covert [MovieDetailsDto] to [MovieDetails]
 */
internal fun MovieDetailsDto.toMovieDetails() = MovieDetails(
    adult = adult,
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection?.toMovieCollection(),
    budget = budget,
    genres = genres.map { it.toGenre() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toProductionCompany() },
    productionCountries = productionCountries.map { it.toProductionCountry() },
    releaseDate = LocalDate.parse(releaseDate),
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.map { it.toSpokenLanguage() },
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

/**
 * Convert [MovieCollectionDto] to [MovieCollection]
 */
private fun MovieCollectionDto.toMovieCollection() = MovieCollection(
    backdropPath = backdropPath,
    id = id,
    name = name,
    posterPath = posterPath,
)

/**
 * Convert [ProductionCompanyDto] to [ProductionCompany]
 */
private fun ProductionCompanyDto.toProductionCompany() = ProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry,
)

/**
 * Convert [ProductionCountryDto] to [ProductionCountry]
 */
private fun ProductionCountryDto.toProductionCountry() = ProductionCountry(
    iso3166 = iso3166,
    name = name,
)

/**
 * Convert [SpokenLanguageDto] to [SpokenLanguage]
 */
private fun SpokenLanguageDto.toSpokenLanguage() = SpokenLanguage(
    englishName = englishName,
    iso639 = iso639,
    name = name,
)

/**
 * Convert [MovieDetails] to [Movie]
 */
internal fun MovieDetails.toMovie(cacheId: Int) = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genres.map { it.id },
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