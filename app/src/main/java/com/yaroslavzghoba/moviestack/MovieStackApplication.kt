package com.yaroslavzghoba.moviestack

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.yaroslavzghoba.common.MOVIE_RELEASE_NOTIFICATION_CHANNEL_ID
import com.yaroslavzghoba.home.di.homeModule
import com.yaroslavzghoba.movie_details.di.movieDetailsModule
import com.yaroslavzghoba.movie_list.di.movieListModule
import com.yaroslavzghoba.moviestack.di.applicationModule
import com.yaroslavzghoba.settings.di.settingsModule
import com.yaroslavzghoba.watched_movies.di.watchedModule
import com.yaroslavzghoba.wish_list.di.wishListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieStackApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        configureDependencyInjection()
        configureNotifications()
    }

    private fun configureDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(this@MovieStackApplication)
            modules(
                applicationModule,
                homeModule,
                movieDetailsModule,
                movieListModule,
                settingsModule,
                watchedModule,
                wishListModule,
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configureNotifications() {
        val notificationChannel = NotificationChannel(
            MOVIE_RELEASE_NOTIFICATION_CHANNEL_ID,
            "Movie release notifications",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}