package com.yaroslavzghoba.watched_movies.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.watched_movies.WatchedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val watchedModule = module {

    includes(dataModule)

    viewModelOf(::WatchedViewModel)
}