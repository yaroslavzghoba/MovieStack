package com.yaroslavzghoba.data.mapper

import com.yaroslavzghoba.data.util.Constants
import com.yaroslavzghoba.database.model.GenreDbo
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.network.model.GenreDto

/**Convert [GenreDbo] to [Genre]*/
internal fun GenreDbo.toGenre() = Genre(
    id = id,
    name = name,
)

/**Convert [GenreDto] to [Genre]*/
internal fun GenreDto.toGenre() = Genre(
    id = id,
    name = name,
)

/**Convert [Genre] to [GenreDbo]*/
internal fun Genre.toDbo() = GenreDbo(
    id = id,
    name = name,
)

/**Get genre by his [Int] identifier*/
internal fun List<Genre>.getById(id: Int): Genre? = this
    .firstOrNull { it.id == id }

/**
 * Convert the list of integers to a string of integers separated by [separator]
 *
 * @receiver A list of integers that should to be converted
 * @param separator between values. By default it's [Constants.GENRE_LIST_SEPARATOR]
 * @return A string of integers separated by [separator]
 */
internal fun List<Int>.toStringList(
    separator: String = Constants.GENRE_LIST_SEPARATOR,
): String = joinToString(separator = separator) { it.toString() }

/**
 * Convert the string of integers separated by [separator] to a list of integers
 *
 * @receiver A string of integers separated by [separator]
 * @param separator between values. By default it's [Constants.GENRE_LIST_SEPARATOR]
 * @return A list of integers that should to be converted
 */
internal fun String.toIntList(
    separator: String = Constants.GENRE_LIST_SEPARATOR,
): List<Int> = this
    .trim()
    .split(separator)
    .map { it.toInt() }