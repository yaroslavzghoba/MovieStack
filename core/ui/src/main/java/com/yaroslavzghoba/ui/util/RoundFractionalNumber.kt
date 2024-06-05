package com.yaroslavzghoba.ui.util

internal fun Float.round(decimals: Int): Float {
    if (decimals <= 0) throw InvalidNumDecimalsPlacesException(
        "Cannot round number to $decimals decimals places!"
    )
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

internal fun Double.round(decimals: Int): Double {
    if (decimals <= 0) throw InvalidNumDecimalsPlacesException(
        "Cannot round number to $decimals decimals places!"
    )
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

internal class InvalidNumDecimalsPlacesException(
    message: String = "Invalid number of decimal places!"
) : Exception(message)