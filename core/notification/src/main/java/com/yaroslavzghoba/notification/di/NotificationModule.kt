package com.yaroslavzghoba.notification.di

import com.yaroslavzghoba.notification.ApplicationNotifier
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val notificationModule = module {

    single {
        ApplicationNotifier(context = androidContext())
    }.bind<ApplicationNotifier>()
}