package com.yaroslavzghoba.network.di

import com.yaroslavzghoba.core.network.BuildConfig
import com.yaroslavzghoba.network.NetworkDataSource
import com.yaroslavzghoba.network.NetworkDataSourceImpl
import com.yaroslavzghoba.network.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

private const val ACCESS_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN

val networkModule = module {

    single<HttpClient> {
        HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url(Constants.BASE_URL)
                contentType(ContentType.Application.Json)
                bearerAuth(ACCESS_TOKEN)
            }
            install(Logging) {
                logger = Logger.ANDROID
            }
        }
    }

    factory<NetworkDataSource> {
        NetworkDataSourceImpl(httpClient = get())
    }
}