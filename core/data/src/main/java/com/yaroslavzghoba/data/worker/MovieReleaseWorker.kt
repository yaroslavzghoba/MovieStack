package com.yaroslavzghoba.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yaroslavzghoba.data.mapper.toWishedMovie
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.notification.ApplicationNotifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class MovieReleaseWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    private val database: ApplicationDatabase =
        ApplicationDatabase.build(context = context)
    private val notifier: ApplicationNotifier =
        ApplicationNotifier(context = context)

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val wishedMovies = database.wishedMovieDao.getAll().first()
                val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
                wishedMovies.forEach { wishedMovieDbo ->
                    val wishedMovie = wishedMovieDbo.toWishedMovie()
                    if (wishedMovie.releaseDate == today) {
                        notifier.notifyAboutMovieRelease(movie = wishedMovie)
                    }
                }
                Result.success()
            } catch (throwable: Throwable) {
                Result.failure()
            }
        }
    }
}