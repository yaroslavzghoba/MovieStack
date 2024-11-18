package com.yaroslavzghoba.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yaroslavzghoba.data.AppRepositoryImpl
import com.yaroslavzghoba.data.mediator.DiscoverMovieMediator
import com.yaroslavzghoba.data.mediator.NowPlayingMovieMediator
import com.yaroslavzghoba.data.mediator.PopularMovieMediator
import com.yaroslavzghoba.data.mediator.TopRatedMovieMediator
import com.yaroslavzghoba.data.mediator.UpcomingMovieMediator
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.di.databaseModule
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.datastore.UserPrefsRepository
import com.yaroslavzghoba.datastore.di.dataStoreModule
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.network.NetworkDataSource
import com.yaroslavzghoba.network.di.networkModule
import com.yaroslavzghoba.notification.di.notificationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val DISCOVER_MOVIE_QUALIFIER = named("DISCOVER_MOVIE")
private val NOW_PLAYING_MOVIE_QUALIFIER = named("NOW_PLAYING_MOVIE")
private val POPULAR_MOVIE_QUALIFIER = named("POPULAR_MOVIE")
private val TOP_RATED_MOVIE_QUALIFIER = named("TOP_RATED_MOVIE")
private val UPCOMING_MOVIE_QUALIFIER = named("UPCOMING_MOVIE")

@OptIn(ExperimentalPagingApi::class)
val dataModule = module {

    includes(dataStoreModule, databaseModule, networkModule, notificationModule)

    single<Pager<Int, DiscoverMovieDbo>>(DISCOVER_MOVIE_QUALIFIER) {
        val network: NetworkDataSource = get()
        val database: ApplicationDatabase = get()
        Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = DiscoverMovieMediator(
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.discoverMovieDao.pagingSource()
            }
        )
    }

    single<Pager<Int, NowPlayingMovieDbo>>(NOW_PLAYING_MOVIE_QUALIFIER) {
        val network: NetworkDataSource = get()
        val database: ApplicationDatabase = get()
        Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = NowPlayingMovieMediator(
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.nowPlayingMovieDao.pagingSource()
            }
        )
    }

    single<Pager<Int, PopularMovieDbo>>(POPULAR_MOVIE_QUALIFIER) {
        val network: NetworkDataSource = get()
        val database: ApplicationDatabase = get()
        Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = PopularMovieMediator(
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.popularMovieDao.pagingSource()
            }
        )
    }

    single<Pager<Int, TopRatedMovieDbo>>(TOP_RATED_MOVIE_QUALIFIER) {
        val network: NetworkDataSource = get()
        val database: ApplicationDatabase = get()
        Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = TopRatedMovieMediator(
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.topRatedMovieDao.pagingSource()
            }
        )
    }

    single<Pager<Int, UpcomingMovieDbo>>(UPCOMING_MOVIE_QUALIFIER) {
        val network: NetworkDataSource = get()
        val database: ApplicationDatabase = get()
        Pager(
            config = PagingConfig(network.PAGE_SIZE),
            remoteMediator = UpcomingMovieMediator(
                database = database,
                network = network,
            ),
            pagingSourceFactory = {
                database.upcomingMovieDao.pagingSource()
            }
        )
    }

    single<ApplicationRepository> {
        val userPrefsRepository: UserPrefsRepository = get()
        val networkDataSource: NetworkDataSource = get()
        val applicationDatabase: ApplicationDatabase = get()
        val discoverMoviePager: Pager<Int, DiscoverMovieDbo> = get(DISCOVER_MOVIE_QUALIFIER)
        val nowPlayingMoviePager: Pager<Int, NowPlayingMovieDbo> = get(NOW_PLAYING_MOVIE_QUALIFIER)
        val popularMoviePager: Pager<Int, PopularMovieDbo> = get(POPULAR_MOVIE_QUALIFIER)
        val topRatedMoviePager: Pager<Int, TopRatedMovieDbo> = get(TOP_RATED_MOVIE_QUALIFIER)
        val upcomingMoviePager: Pager<Int, UpcomingMovieDbo> = get(UPCOMING_MOVIE_QUALIFIER)
        AppRepositoryImpl(
            context = androidContext(),
            userPrefsRepository = userPrefsRepository,
            database = applicationDatabase,
            network = networkDataSource,
            discoverMoviePager = discoverMoviePager,
            nowPlayingMoviePager = nowPlayingMoviePager,
            popularMoviePager = popularMoviePager,
            topRatedMoviePager = topRatedMoviePager,
            upcomingMoviePager = upcomingMoviePager,
        )
    }
}