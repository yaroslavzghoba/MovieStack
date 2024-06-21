package com.yaroslavzghoba.settings.util

import androidx.annotation.StringRes

/**
 * Represent a single option of multi-option preference
 */
abstract class Option<T>(
    val value: T,
    @StringRes val titleRes: Int,
)
