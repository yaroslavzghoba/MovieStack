package com.yaroslavzghoba.settings.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {

    includes(dataModule)

    viewModelOf(::SettingsViewModel)
}