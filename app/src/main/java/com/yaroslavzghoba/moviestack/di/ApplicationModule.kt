package com.yaroslavzghoba.moviestack.di

import com.yaroslavzghoba.home.di.homeModule
import com.yaroslavzghoba.movie_details.di.movieDetailsModule
import com.yaroslavzghoba.movie_list.di.movieListModule
import com.yaroslavzghoba.moviestack.MainViewModel
import com.yaroslavzghoba.settings.di.settingsModule
import com.yaroslavzghoba.watched_movies.di.watchedModule
import com.yaroslavzghoba.wish_list.di.wishListModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val applicationModule = module {

    includes(
        homeModule,
        movieDetailsModule,
        movieListModule,
        settingsModule,
        watchedModule,
        wishListModule,
    )

    viewModelOf(::MainViewModel)
}