package com.yaroslavzghoba.movie_list.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.movie_list.MovieListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieListModule = module {

    includes(dataModule)

    viewModelOf(::MovieListViewModel)
}