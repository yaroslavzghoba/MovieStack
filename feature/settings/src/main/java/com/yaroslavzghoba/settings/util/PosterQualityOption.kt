package com.yaroslavzghoba.settings.util

import androidx.annotation.StringRes
import com.yaroslavzghoba.model.util.PosterQuality
import com.yaroslavzghoba.settings.R

internal sealed class PosterQualityOption(
    posterQuality: PosterQuality,
    @StringRes titleRes: Int,
) : Option<PosterQuality>(
    value = posterQuality,
    titleRes = titleRes,
) {

    data object Low : PosterQualityOption(
        posterQuality = PosterQuality.W92,
        titleRes = R.string.posters_quality_low_option,
    )

    data object Middle : PosterQualityOption(
        posterQuality = PosterQuality.W342,
        titleRes = R.string.posters_quality_middle_option,
    )

    data object High : PosterQualityOption(
        posterQuality = PosterQuality.W780,
        titleRes = R.string.posters_quality_high_option,
    )
}