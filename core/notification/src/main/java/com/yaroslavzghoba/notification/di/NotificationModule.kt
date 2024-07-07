package com.yaroslavzghoba.notification.di

import android.content.Context
import com.yaroslavzghoba.notification.ApplicationNotifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NotificationModule {

    @Provides
    @Singleton
    fun provideApplicationNotifier(
        @ApplicationContext context: Context,
    ): ApplicationNotifier {
        return ApplicationNotifier(context = context)
    }
}