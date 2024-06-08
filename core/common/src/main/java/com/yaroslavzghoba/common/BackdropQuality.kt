package com.yaroslavzghoba.common

import androidx.compose.runtime.compositionLocalOf

enum class BackdropQuality(val path: String) {
    W300(path = "w300"),
    W780(path = "w780"),
    W1280(path = "w1280"),
}

val LocalBackdropQuality = compositionLocalOf { BackdropQuality.W780 }