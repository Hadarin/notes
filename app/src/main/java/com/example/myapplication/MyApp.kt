package com.example.myapplication

import android.app.Application
import com.data.di.dataModule
import com.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                dataModule,
                presentationModule
            )
        }
    }
}