package com.yaroslavzghoba.database.di

import android.content.Context
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.ApplicationRoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return ApplicationDatabase(
            database = ApplicationRoomDb.getInstance(context = context)
        )
    }
}