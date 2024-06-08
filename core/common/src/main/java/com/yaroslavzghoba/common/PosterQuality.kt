package com.yaroslavzghoba.common

import androidx.compose.runtime.compositionLocalOf

enum class PosterQuality(val path: String) {
    W92(path = "w92"),
    W154(path = "w154"),
    W185(path = "w185"),
    W342(path = "w342"),
    W500(path = "w500"),
    W780(path = "w780"),
}

val LocalPosterQuality = compositionLocalOf { PosterQuality.W342 }