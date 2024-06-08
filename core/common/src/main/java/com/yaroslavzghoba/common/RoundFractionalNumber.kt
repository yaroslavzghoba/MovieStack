package com.yaroslavzghoba.common

/**
 * Round [Float] to [decimals] places.
 *
 * @receiver [Float] that must to be rounded
 * @return [Float] rounded to [decimals] places
 * @throws IllegalArgumentException if [decimals] <= 0
 * @sample roundFloatSample
 */
fun Float.round(decimals: Int): Float {
    require(decimals > 0) {
        "Cannot round number to $decimals decimals places!"
    }
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

/**
 * Round [Double] to [decimals] places. For example:
 *
 * @receiver [Double] that must to be rounded
 * @return [Double] rounded to [decimals] places
 * @throws IllegalArgumentException if [decimals] <= 0
 * @sample roundDoubleSample
 */
fun Double.round(decimals: Int): Double {
    require(decimals > 0) {
        "Cannot round number to $decimals decimals places!"
    }
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

private fun roundFloatSample() {
    val pi: Float = Math.PI.toFloat()
    println(pi)  // 3.1415927
    println(pi.round(2))  // 3.14
}

private fun roundDoubleSample() {
    val pi: Double = Math.PI
    println(pi)  // 3.141592653589793
    println(pi.round(2))  // 3.14
}