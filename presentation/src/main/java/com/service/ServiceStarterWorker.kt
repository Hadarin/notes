package com.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ServiceStarterWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            val intent = Intent(applicationContext, MyForegroundService::class.java)
            applicationContext.startForegroundService(intent)
            Result.success()
        } catch (e: Exception) {
            Log.e("ServiceStarter", "Failed to start service", e)
            Result.failure()
        }
    }
}