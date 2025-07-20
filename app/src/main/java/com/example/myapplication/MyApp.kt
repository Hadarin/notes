package com.example.myapplication

import android.app.Application
import com.data.di.dataModule
import com.data.di.networkModule
import com.data.di.socketModule
import com.di.presentationModule
import com.example.myapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                dataModule,
                networkModule,
                presentationModule,
                socketModule,
                appModule
            )
        }
    }
}