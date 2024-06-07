package com.yaroslavzghoba.common

/**
 * Used as an identifier for individual movie collections that exist in the application.
 * The [POPULAR] category represents a collection of popular movies, for example
 */
enum class MovieCategory {

    /**Represent a collection of discover movies*/
    DISCOVER,

    /**Represent a collection of popular movies*/
    POPULAR,

    /**Represent a collection of now playing movies*/
    NOW_PLAYING,

    /**Represent a collection of top rated movies*/
    TOP_RATED,

    /**Represent a collection of upcoming movies*/
    UPCOMING,
}

/**
 * Convert [String] to [MovieCategory].
 *
 * @receiver a name of [MovieCategory] entity
 * @return [IllegalArgumentException] if there is no constant with the specified name
 */
fun String.toMovieCategory(): MovieCategory = MovieCategory.valueOf(this)