package com.yaroslavzghoba.home.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {

    includes(dataModule)

    viewModelOf(::HomeViewModel)
}