package com.yaroslavzghoba.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.yaroslavzghoba.data.mapper.toDiscoverMovieDbo
import com.yaroslavzghoba.data.mapper.toMovie
import com.yaroslavzghoba.database.ApplicationDatabase
import com.yaroslavzghoba.database.model.DiscoverMovieDbo
import com.yaroslavzghoba.network.NetworkDataSource
import retrofit2.HttpException
import java.io.IOException

private var pagesLoaded: Int = 0

@OptIn(ExperimentalPagingApi::class)
internal class DiscoverMovieMediator(
    private val database: ApplicationDatabase,
    private val network: NetworkDataSource,
) : RemoteMediator<Int, DiscoverMovieDbo>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DiscoverMovieDbo>
    ): MediatorResult {

        // Calculate page number that must to be loaded
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                when (state.lastItemOrNull()) {
                    null -> 1
                    else -> pagesLoaded + 1
                }
            }
        }

        return try {
            // Load the new page from network
            val response = network.getMovies(page = page)

            // Cache the loaded movies
            if (loadType == LoadType.REFRESH) {
                database.discoverMovieDao.deleteAll()
            }
            database.discoverMovieDao.upsertAll(
                movies = response.results.mapIndexed { index, movieDto ->
                    val cacheId = (response.page - 1) * state.config.pageSize + index + 1
                    movieDto
                        .toMovie(cacheId = cacheId)
                        .toDiscoverMovieDbo()
                }
            )

            pagesLoaded++
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