package com.yaroslavzghoba.database.di

import com.yaroslavzghoba.database.ApplicationDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {

    single {
        ApplicationDatabase.build(context = androidContext())
    }.bind<ApplicationDatabase>()
}