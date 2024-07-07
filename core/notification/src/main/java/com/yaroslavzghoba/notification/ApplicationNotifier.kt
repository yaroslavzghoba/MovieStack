package com.yaroslavzghoba.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.yaroslavzghoba.common.MOVIE_RELEASE_NOTIFICATION_CHANNEL_ID
import com.yaroslavzghoba.model.MovieCommon
import kotlin.random.Random

class ApplicationNotifier(private val context: Context) {

    private val notificationManager = context
        .getSystemService(NotificationManager::class.java)

    fun notifyAboutMovieRelease(movie: MovieCommon) {
        val title = context.getString(R.string.release_movie_notification_title)
        val text = context
            .getString(R.string.release_movie_notification_description, movie.title)
        val notification =
            NotificationCompat.Builder(context, MOVIE_RELEASE_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .build()
        notificationManager.notify(Random.nextInt(), notification)
    }
}