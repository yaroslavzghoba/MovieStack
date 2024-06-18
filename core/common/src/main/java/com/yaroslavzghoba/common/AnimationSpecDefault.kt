package com.yaroslavzghoba.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

// TODO: Consider providing animation specs using Composition Local
fun <T> animationSpecDefault() = spring<T>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessMediumLow,
)