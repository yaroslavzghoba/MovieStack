package com.yaroslavzghoba.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.yaroslavzghoba.common.LocalBackdropQuality
import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.ui.util.Constants

// TODO: Add placeholder for movie backdrop
@Composable
fun MovieBackdrop(
    backdropPath: String?,
    modifier: Modifier = Modifier,
    backdropQuality: BackdropQuality = LocalBackdropQuality.current
) {
    val backdropUrl = "${Constants.IMAGE_BASE_URL}${backdropQuality.path}${backdropPath}"
    AsyncImage(
        model = backdropUrl,
        contentDescription = null,
        modifier = modifier.aspectRatio(16f / 9f),
        contentScale = ContentScale.Crop,
    )
}