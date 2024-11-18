package com.yaroslavzghoba.movie_details.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.movie_details.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieDetailsModule = module {

    includes(dataModule)

    viewModelOf(::MovieDetailsViewModel)
}