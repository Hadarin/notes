package com.example.myapplication.di

import com.domain.notification.HandlePersonNotificationUseCase
import com.domain.notification.NotificationHandler
import com.example.myapplication.notifications.AppNotificationHandlerImpl
import org.koin.dsl.module

val appModule = module {

    // Provide notification handler
    single<NotificationHandler> {
        AppNotificationHandlerImpl(context = get())
    }

    // Provide use case
    single<HandlePersonNotificationUseCase> {
        HandlePersonNotificationUseCase(notificationHandler = get())
    }
}