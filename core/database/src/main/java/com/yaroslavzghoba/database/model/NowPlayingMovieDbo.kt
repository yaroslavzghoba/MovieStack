package com.yaroslavzghoba.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing_movies_cache")
data class NowPlayingMovieDbo(

    @ColumnInfo("adult")
    val adult: Boolean,

    @ColumnInfo("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo("genre_ids")
    val genreIds: String,

    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("original_language")
    val originalLanguage: String,

    @ColumnInfo("original_title")
    val originalTitle: String,

    @ColumnInfo("overview")
    val overview: String,

    @ColumnInfo("popularity")
    val popularity: Double,

    @ColumnInfo("poster_path")
    val posterPath: String?,

    @ColumnInfo("release_date")
    val releaseDate: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("video")
    val video: Boolean,

    @ColumnInfo("vote_average")
    val voteAverage: Double,

    @ColumnInfo("vote_count")
    val voteCount: Int,

    /**Used as a primary key instead of `id`, to preserve the same sequence as when loading*/
    @PrimaryKey
    @ColumnInfo("cache_id")
    val cacheId: Int,
)
