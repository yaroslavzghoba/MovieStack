package com.yaroslavzghoba.data.di

import android.content.Context
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
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.database.model.PopularMovieDbo
import com.yaroslavzghoba.database.model.TopRatedMovieDbo
import com.yaroslavzghoba.database.model.UpcomingMovieDbo
import com.yaroslavzghoba.datastore.UserPrefsRepository
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideDiscoverMoviePager(
        database: ApplicationDatabase,
        network: NetworkDataSource,
    ): Pager<Int, DiscoverMovieDbo> {
        return Pager(
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideNowPlayingMoviePager(
        database: ApplicationDatabase,
        network: NetworkDataSource,
    ): Pager<Int, NowPlayingMovieDbo> {
        return Pager(
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePopularMoviePager(
        database: ApplicationDatabase,
        network: NetworkDataSource,
    ): Pager<Int, PopularMovieDbo> {
        return Pager(
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideTopRatedMoviePager(
        database: ApplicationDatabase,
        network: NetworkDataSource,
    ): Pager<Int, TopRatedMovieDbo> {
        return Pager(
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUpcomingMoviePager(
        database: ApplicationDatabase,
        network: NetworkDataSource,
    ): Pager<Int, UpcomingMovieDbo> {
        return Pager(
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

    @Provides
    fun provideApplicationRepository(
        @ApplicationContext context: Context,
        userPrefsRepository: UserPrefsRepository,
        database: ApplicationDatabase,
        network: NetworkDataSource,
        discoverMoviePager: Pager<Int, DiscoverMovieDbo>,
        nowPlayingMoviePager: Pager<Int, NowPlayingMovieDbo>,
        popularMoviePager: Pager<Int, PopularMovieDbo>,
        topRatedMoviePager: Pager<Int, TopRatedMovieDbo>,
        upcomingMoviePager: Pager<Int, UpcomingMovieDbo>,
    ): ApplicationRepository {
        return AppRepositoryImpl(
            context = context,
            userPrefsRepository = userPrefsRepository,
            database = database,
            network = network,
            discoverMoviePager = discoverMoviePager,
            nowPlayingMoviePager = nowPlayingMoviePager,
            popularMoviePager = popularMoviePager,
            topRatedMoviePager = topRatedMoviePager,
            upcomingMoviePager = upcomingMoviePager,
        )
    }
}