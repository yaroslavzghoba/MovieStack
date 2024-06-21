package com.yaroslavzghoba.common

import androidx.compose.runtime.compositionLocalOf
import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.model.util.PosterQuality

/**Represent current posters quality configuration*/
val LocalPosterQuality = compositionLocalOf { PosterQuality.W342 }

/**Represent current backdrops quality configuration*/
val LocalBackdropQuality = compositionLocalOf { BackdropQuality.W780 }