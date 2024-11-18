package com.yaroslavzghoba.network.di

import com.yaroslavzghoba.network.NetworkDataSource
import com.yaroslavzghoba.network.NetworkDataSourceImpl
import com.yaroslavzghoba.network.service.MovieService
import com.yaroslavzghoba.network.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }.bind<OkHttpClient>()

    single {
        val client: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }.bind<Retrofit>()

    single {
        val retrofit: Retrofit = get()
        retrofit.create(MovieService::class.java)
    }.bind<MovieService>()

    factory {
        val movieService: MovieService = get()
        NetworkDataSourceImpl(movieService = movieService)
    }.bind<NetworkDataSource>()
}