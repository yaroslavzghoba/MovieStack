package com.yaroslavzghoba.wish_list.di

import com.yaroslavzghoba.data.di.dataModule
import com.yaroslavzghoba.wish_list.WishListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val wishListModule = module {

    includes(dataModule)

    viewModelOf(::WishListViewModel)
}