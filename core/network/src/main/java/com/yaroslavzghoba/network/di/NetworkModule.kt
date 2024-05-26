package com.yaroslavzghoba.network.di

import com.yaroslavzghoba.network.Constants
import com.yaroslavzghoba.network.NetworkDataSource
import com.yaroslavzghoba.network.NetworkDataSourceImpl
import com.yaroslavzghoba.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    fun provideNetworkDataSource(movieService: MovieService): NetworkDataSource {
        return NetworkDataSourceImpl(movieService = movieService)
    }
}