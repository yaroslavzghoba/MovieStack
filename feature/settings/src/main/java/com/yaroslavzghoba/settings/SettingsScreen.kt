package com.yaroslavzghoba.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yaroslavzghoba.settings.component.MultiOptionSelector
import com.yaroslavzghoba.settings.util.BackdropQualityOption
import com.yaroslavzghoba.settings.util.PosterQualityOption
import com.yaroslavzghoba.settings.util.ThemeModeOption

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
) {
    val themeModeOptions =
        listOf(ThemeModeOption.FollowSystem, ThemeModeOption.Light, ThemeModeOption.Dark)
    val selectedThemeMode = themeModeOptions
        .first { it.value == viewModel.userPreferences.themeMode }
    val posterQualityOptions =
        listOf(PosterQualityOption.Low, PosterQualityOption.Middle, PosterQualityOption.High)
    val selectedPosterQuality = posterQualityOptions
        .firstOrNull { it.value == viewModel.userPreferences.posterQuality }
    val backdropQualityOptions =
        listOf(BackdropQualityOption.Low, BackdropQualityOption.Middle, BackdropQualityOption.High)
    val selectedBackdropQuality = backdropQualityOptions
        .firstOrNull { it.value == viewModel.userPreferences.backdropQuality }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding(),
    ) {
        ListItem(
            headlineContent = {
                @StringRes val textRes = R.string.use_achromatic_colors_title
                Text(text = stringResource(id = textRes))
            },
            supportingContent = {
                @StringRes val textRes = R.string.use_achromatic_colors_description
                Text(text = stringResource(id = textRes))
            },
            trailingContent = {
                Switch(
                    checked = viewModel.userPreferences.useAchromaticColors,
                    onCheckedChange = { checked ->
                        val event = SettingsUiEvent.UserPreferencesChanged(
                            userPreferences = viewModel.userPreferences.copy(
                                useAchromaticColors = checked,
                            )
                        )
                        viewModel.onEvent(event = event)
                    }
                )
            },
        )
        ListItem(
            headlineContent = {
                @StringRes val textRes = R.string.dynamic_theme_title
                Text(text = stringResource(id = textRes))
            },
            supportingContent = {
                @StringRes val textRes = R.string.dynamic_theme_description
                Text(text = stringResource(id = textRes))
            },
            trailingContent = {
                Switch(
                    checked = viewModel.userPreferences.dynamicTheme,
                    onCheckedChange = { checked ->
                        val event = SettingsUiEvent.UserPreferencesChanged(
                            userPreferences = viewModel.userPreferences.copy(
                                dynamicTheme = checked,
                            )
                        )
                        viewModel.onEvent(event = event)
                    },
                    enabled = !viewModel.userPreferences.useAchromaticColors,
                )
            },
        )
        ListItem(
            headlineContent = {
                Text(text = stringResource(id = R.string.theme_mode_title))
            },
            supportingContent = {
                MultiOptionSelector(
                    selectedOption = selectedThemeMode,
                    onOptionSelected = { option ->
                        val event = SettingsUiEvent.UserPreferencesChanged(
                            userPreferences = viewModel.userPreferences.copy(
                                themeMode = option.value,
                            )
                        )
                        viewModel.onEvent(event = event)
                    },
                    options = themeModeOptions,
                )
            }
        )
        ListItem(
            headlineContent = {
                Text(text = stringResource(id = R.string.posters_quality_title))
            },
            supportingContent = {
                MultiOptionSelector(
                    selectedOption = selectedPosterQuality,
                    onOptionSelected = { option ->
                        val event = SettingsUiEvent.UserPreferencesChanged(
                            userPreferences = viewModel.userPreferences.copy(
                                posterQuality = option.value,
                            )
                        )
                        viewModel.onEvent(event = event)
                    },
                    options = posterQualityOptions,
                )
            }
        )
        ListItem(
            headlineContent = {
                Text(text = stringResource(id = R.string.backdrops_quality_title))
            },
            supportingContent = {
                MultiOptionSelector(
                    selectedOption = selectedBackdropQuality,
                    onOptionSelected = { option ->
                        val event = SettingsUiEvent.UserPreferencesChanged(
                            userPreferences = viewModel.userPreferences.copy(
                                backdropQuality = option.value,
                            )
                        )
                        viewModel.onEvent(event = event)
                    },
                    options = backdropQualityOptions,
                )
            }
        )
    }
}