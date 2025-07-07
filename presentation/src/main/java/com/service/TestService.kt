package com.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestService: Service() {

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        Log.d("Test service", "Service started")

        serviceScope.launch {
            repeat(120) { i ->
                delay(3000L) // Wait for 3 seconds
                Log.d("Test service", "Work with number $i")
            }
            Log.d("Test service", "Work finished, stopping service")
            stopSelf()
        }

        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Test Service", "Service created")
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.d("Test service", "Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // not bounded
    }
}