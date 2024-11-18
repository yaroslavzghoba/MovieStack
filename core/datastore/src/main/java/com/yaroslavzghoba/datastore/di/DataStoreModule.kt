package com.yaroslavzghoba.datastore.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.yaroslavzghoba.datastore.UserPrefsRepository
import com.yaroslavzghoba.datastore.UserPrefsRepositoryImpl
import com.yaroslavzghoba.datastore.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore(
    name = Constants.USER_PREFS_STORAGE_NAME,
)

val dataStoreModule = module {

    single {
        UserPrefsRepositoryImpl(dataStore = androidContext().dataStore)
    }.bind<UserPrefsRepository>()
}