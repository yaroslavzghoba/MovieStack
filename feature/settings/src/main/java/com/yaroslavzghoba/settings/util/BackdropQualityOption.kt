package com.yaroslavzghoba.settings.util

import androidx.annotation.StringRes
import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.settings.R

internal sealed class BackdropQualityOption(
    backdropQuality: BackdropQuality,
    @StringRes titleRes: Int,
) : Option<BackdropQuality>(
    value = backdropQuality,
    titleRes = titleRes,
) {

    data object Low : BackdropQualityOption(
        backdropQuality = BackdropQuality.W300,
        titleRes = R.string.posters_quality_low_option,
    )

    data object Middle : BackdropQualityOption(
        backdropQuality = BackdropQuality.W780,
        titleRes = R.string.posters_quality_middle_option,
    )

    data object High : BackdropQualityOption(
        backdropQuality = BackdropQuality.W1280,
        titleRes = R.string.posters_quality_high_option,
    )
}
