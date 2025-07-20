package com.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyBackgroundWorker (
appContext: Context,
workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams ) {

    val tmp : WorkManager = WorkManager.getInstance()


    override suspend fun doWork(): Result {
        for (i in 1..10) {
            Log.d("MyWorker", "Doing work $i")
            delay(3000)
        }
        Log.d("MyWorker", "Work completed")
        return Result.success()
    }
}