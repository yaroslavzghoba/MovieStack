package com.yaroslavzghoba.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wished_movies")
data class WishedMovieEntity(

    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("poster_path")
    val posterPath: String?,

    @ColumnInfo("scheduled_viewing_at")
    val scheduledViewingAt: String,
)
