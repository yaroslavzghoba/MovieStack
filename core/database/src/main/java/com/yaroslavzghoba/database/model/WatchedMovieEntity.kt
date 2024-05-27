package com.yaroslavzghoba.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movies")
data class WatchedMovieEntity(

    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("poster_path")
    val posterPath: String?,

    @ColumnInfo("personal_evaluation")
    val personalEvaluation: Int?,
)
