package com.yaroslavzghoba.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.yaroslavzghoba.data.mapper.toMovie
import com.yaroslavzghoba.data.mapper.toNowPlayingMovieDbo
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.NowPlayingMovieDbo
import com.yaroslavzghoba.network.NetworkDataSource
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class NowPlayingMovieMediator(
    private val database: ApplicationDatabase,
    private val network: NetworkDataSource,
) : RemoteMediator<Int, NowPlayingMovieDbo>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingMovieDbo>
    ): MediatorResult {

        // Calculate page number that must to be loaded
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                when (val lastItem = state.lastItemOrNull()) {
                    null -> 1
                    else -> (lastItem.id / state.config.pageSize) + 1
                }
            }
        }

        return try {
            // Load the new page from network
            val response = network.getNowPlayingMovies(page = page)

            // Cache the loaded movies
            if (loadType == LoadType.REFRESH) {
                database.nowPlayingMovieDao.deleteAll()
            }
            database.nowPlayingMovieDao.upsertAll(
                movies = response.results.map { it.toMovie().toNowPlayingMovieDbo() }
            )

            MediatorResult.Success(
                endOfPaginationReached = response.page == response.totalPages
            )
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}