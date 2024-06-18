package com.yaroslavzghoba.datastore.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.yaroslavzghoba.datastore.UserPrefsRepository
import com.yaroslavzghoba.datastore.UserPrefsRepositoryImpl
import com.yaroslavzghoba.datastore.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = Constants.USER_PREFS_STORAGE_NAME,
)

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPrefsRepository(
        @ApplicationContext context: Context,
    ): UserPrefsRepository {
        return UserPrefsRepositoryImpl(dataStore = context.dataStore)
    }
}