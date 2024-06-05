package com.yaroslavzghoba.data

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.yaroslavzghoba.data.mapper.toDbo
import com.yaroslavzghoba.data.mapper.toGenre
import com.yaroslavzghoba.data.mapper.toMovie
import com.yaroslavzghoba.data.mapper.toWatchedMovie
import com.yaroslavzghoba.data.mapper.toWishedMovie
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.Genre
import com.yaroslavzghoba.model.Movie
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.model.WishedMovie
import com.yaroslavzghoba.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class AppRepositoryImpl internal constructor(
    private val database: ApplicationDatabase,
    private val network: NetworkDataSource,
    private val discoverMoviePager: Pager<Int, DiscoverMovieDbo>,
    private val nowPlayingMoviePager: Pager<Int, NowPlayingMovieDbo>,
    private val popularMoviePager: Pager<Int, PopularMovieDbo>,
    private val topRatedMoviePager: Pager<Int, TopRatedMovieDbo>,
    private val upcomingMoviePager: Pager<Int, UpcomingMovieDbo>,
) : ApplicationRepository {

    // TODO: Use the language selected by user
    // TODO: Consider creating backups to restore data if network request is unsuccessful
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


    
    override suspend fun upsertWatchedMovie(movie: WatchedMovie) {
        database.watchedMovieDao.upsert(movie = movie.toDbo())
    }

    override suspend fun upsertWatchedMovies(movies: List<WatchedMovie>) {
        database.watchedMovieDao.upsertAll(
            movies = movies.map { it.toDbo() }
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

    override fun getWatchedMovieById(id: Int): Flow<WatchedMovie> {
        return database.watchedMovieDao
            .getById(id = id)
            .map { it.toWatchedMovie() }
    }

    override fun getAllWatchedMovies(): Flow<List<WatchedMovie>> {
        return database.watchedMovieDao.getAll().map { watchedMovieDbos ->
            watchedMovieDbos.map { it.toWatchedMovie() }
        }
    }



    override suspend fun upsertWishedMovie(movie: WishedMovie) {
        database.wishedMovieDao.upsert(movie = movie.toDbo())
    }

    override suspend fun upsertWishedMovies(movies: List<WishedMovie>) {
        database.wishedMovieDao.upsertAll(
            movies = movies.map { it.toDbo() }
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

    override fun getWishedMovieById(id: Int): Flow<WishedMovie> {
        return database.wishedMovieDao
            .getById(id = id)
            .map { it.toWishedMovie() }
    }

    override fun getAllWishedMovies(): Flow<List<WishedMovie>> {
        return database.wishedMovieDao.getAll().map { wishedMovieDbos ->
            wishedMovieDbos.map { it.toWishedMovie() }
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

    // TODO: Consider creating cache for searched movies
    // TODO: Consider creating custom request state holder class
    override fun getMoviesByQuery(query: String): Flow<Result<List<Movie>>> {
        return flow {
            val result = try {
                val movies = network.getMoviesByQuery(query = query).results
                    .map { it.toMovie(cacheId = 0) }
                Result.success(movies)
            } catch (exception: HttpException) {
                Result.failure(exception = exception)
            } catch (exception: IOException) {
                Result.failure(exception = exception)
            }
            emit(result)
        }
    }
}