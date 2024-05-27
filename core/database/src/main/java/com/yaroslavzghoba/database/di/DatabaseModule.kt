package com.yaroslavzghoba.database.di

import android.content.Context
import com.yaroslavzghoba.database.MovieStackDatabase
import com.yaroslavzghoba.database.MovieStackRoomDB
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
    fun provideDatabase(@ApplicationContext context: Context): MovieStackDatabase {
        return MovieStackDatabase(
            database = MovieStackRoomDB.getInstance(context = context)
        )
    }
}