package com.yaroslavzghoba.data

import android.content.Context
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.yaroslavzghoba.data.mapper.toDbo
import com.yaroslavzghoba.data.mapper.toGenre
import com.yaroslavzghoba.data.mapper.toMovie
import com.yaroslavzghoba.data.mapper.toMovieDetails
import com.yaroslavzghoba.data.mapper.toWatchedMovie
import com.yaroslavzghoba.data.mapper.toWishedMovie
import com.yaroslavzghoba.data.mediator.SearchedMovieMediator
import com.yaroslavzghoba.data.util.Constants.MOVIE_RELEASE_WORK_NAME
import com.yaroslavzghoba.data.util.getInitialDelay
import com.yaroslavzghoba.data.worker.MovieReleaseWorker
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.datastore.UserPrefsRepository
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.MovieDetails
import com.yaroslavzghoba.model.UserPreferences
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import com.yaroslavzghoba.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.LocalTime
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

class AppRepositoryImpl internal constructor(
    private val context: Context,
    private val userPrefsRepository: UserPrefsRepository,
    private val database: ApplicationDatabase,
    private val network: NetworkDataSource,
    private val discoverMoviePager: Pager<Int, DiscoverMovieDbo>,
    private val nowPlayingMoviePager: Pager<Int, NowPlayingMovieDbo>,
    private val popularMoviePager: Pager<Int, PopularMovieDbo>,
    private val topRatedMoviePager: Pager<Int, TopRatedMovieDbo>,
    private val upcomingMoviePager: Pager<Int, UpcomingMovieDbo>,
) : ApplicationRepository {

    override fun getUserPreferences(): Flow<UserPreferences> {
        return userPrefsRepository.getUserPreferences()
    }

    override suspend fun updateUserPreferences(userPreferences: UserPreferences) {
        userPrefsRepository.updateUserPreferences(userPreferences = userPreferences)
    }


    // ==========WATCHED MOVIES==========
    override suspend fun upsertWatchedMovie(movie: WatchedMovie) {
        database.watchedMovieDao.upsert(movie = movie.toDbo())
    }

    override suspend fun upsertWatchedMovies(movies: List<WatchedMovie>) {
        database.watchedMovieDao.upsertAll(
            movies = movies.map { it.toDbo() }
        )
    }

    override suspend fun addMovieToWatched(movie: MovieDetails) {
        database.watchedMovieDao.upsert(
            movie = movie
                .toMovie(cacheId = 0)
                .toWatchedMovie(votePersonal = null, databaseId = 0)
                .toDbo()
        )
    }

    override suspend fun moveWatchedMovieToWished(movie: MovieDetails) {
        database.watchedMovieDao.deleteMoviesById(id = movie.id)
        database.wishedMovieDao.upsert(
            movie = movie
                .toMovie(cacheId = 0)
                .toWishedMovie(scheduledViewingAt = null, databaseId = 0)
                .toDbo()
        )
    }

    override suspend fun deleteWatchedMovie(movie: WatchedMovie) {
        database.watchedMovieDao.delete(movie = movie.toDbo())
    }

    override suspend fun deleteWatchedMovies(movies: List<WatchedMovie>) {
        database.watchedMovieDao.deleteAll(
            movies = movies.map { it.toDbo() }
        )
    }

    override suspend fun deleteWatchedMoviesById(id: Int) {
        database.watchedMovieDao.deleteMoviesById(id = id)
    }

    override fun getAllWatchedMovies(): Flow<List<WatchedMovie>> {
        return database.watchedMovieDao.getAll().map { watchedMovieDbos ->
            watchedMovieDbos.map { it.toWatchedMovie() }
        }
    }

    override fun countWatchedMoviesById(id: Int): Flow<Int> {
        return database.watchedMovieDao.countMovies(id = id)
    }


    // ==========WISHED MOVIES==========
    override suspend fun upsertWishedMovie(movie: WishedMovie) {
        database.wishedMovieDao.upsert(movie = movie.toDbo())
    }

    override suspend fun upsertWishedMovies(movies: List<WishedMovie>) {
        database.wishedMovieDao.upsertAll(
            movies = movies.map { it.toDbo() }
        )
    }

    override suspend fun addMovieToWished(movie: MovieDetails) {
        database.wishedMovieDao.upsert(
            movie = movie
                .toMovie(cacheId = 0)
                .toWishedMovie(scheduledViewingAt = null, databaseId = 0)
                .toDbo()
        )
    }

    override suspend fun moveWishedMovieToWatched(movie: MovieDetails) {
        database.wishedMovieDao.deleteMoviesById(id = movie.id)
        database.watchedMovieDao.upsert(
            movie = movie
                .toMovie(cacheId = 0)
                .toWatchedMovie(votePersonal = null, databaseId = 0)
                .toDbo()
        )
    }

    override suspend fun deleteWishedMovie(movie: WishedMovie) {
        database.wishedMovieDao.delete(movie = movie.toDbo())
    }

    override suspend fun deleteWishedMovies(movies: List<WishedMovie>) {
        database.wishedMovieDao.deleteAll(
            movies = movies.map { it.toDbo() }
        )
    }

    override suspend fun deleteWishedMoviesById(id: Int) {
        database.wishedMovieDao.deleteMoviesById(id = id)
    }

    override fun getAllWishedMovies(): Flow<List<WishedMovie>> {
        return database.wishedMovieDao.getAll().map { wishedMovieDbos ->
            wishedMovieDbos.map { it.toWishedMovie() }
        }
    }

    override fun countWishedMoviesById(id: Int): Flow<Int> {
        return database.wishedMovieDao.countMovies(id = id)
            .onEach { Log.d("Data layer", "countWatchedMoviesById: $it") }
    }


    // ==========NETWORK==========
    // TODO: Use the language selected by user
    // TODO: Consider creating backups to restore data if network request is unsuccessful
    // TODO: Store movie genres in the database
    override suspend fun updateGenres() {
        database.genreDao.deleteAll()
        try {
            val response = network.getAllGenres()
            database.genreDao.upsertAll(
                genres = response.genres.map { genreDto ->
                    genreDto.toGenre().toDbo()
                }
            )
        } catch (exception: HttpException) {
            // TODO: Handle exception
        } catch (exception: IOException) {
            // TODO: Handle exception
        }
    }

    override fun getAllGenres(): Flow<List<Genre>> {
        return database.genreDao.getAll().map { genreDbos ->
            genreDbos.map { it.toGenre() }
        }
    }

    override suspend fun clearDiscoverMoviesCache() {
        database.discoverMovieDao.deleteAll()
    }

    override fun getDiscoverMovies(): Flow<PagingData<Movie>> {
        return discoverMoviePager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun clearNowPlayingMoviesCache() {
        database.nowPlayingMovieDao.deleteAll()
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return nowPlayingMoviePager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun clearPopularMoviesCache() {
        database.popularMovieDao.deleteAll()
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return popularMoviePager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun clearTopRatedMoviesCache() {
        database.topRatedMovieDao.deleteAll()
    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return topRatedMoviePager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun clearUpcomingMoviesCache() {
        database.upcomingMovieDao.deleteAll()
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return upcomingMoviePager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun clearSearchedMoviesCache() {
        database.searchedMovieDao.deleteAll()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesByQuery(query: String): Flow<PagingData<Movie>> {
        require(query.isNotBlank()) { "The search query cannot be blank" }
        return Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = SearchedMovieMediator(
                query = query,
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.searchedMovieDao.pagingSource()
            },
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override suspend fun getMovieDetails(id: Int): Flow<Result<MovieDetails>> {
        return flow {
            val result: Result<MovieDetails> = try {
                Result.success(network.getMovieDetails(id = id).toMovieDetails())
            } catch (exception: HttpException) {
                Result.failure(exception = exception)
            } catch (exception: IOException) {
                Result.failure(exception = exception)
            }
            emit(result)
        }
    }


    // ==========NOTIFICATIONS==========
    override fun launchMovieReleaseNotifications() {
        // TODO: Inject the target time for notifications from a local storage
        val targetTime = LocalTime(hour = 12, minute = 0)
        val delay = getInitialDelay(targetTime = targetTime)
        val request = PeriodicWorkRequestBuilder<MovieReleaseWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                MOVIE_RELEASE_WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request,
            )
    }
}