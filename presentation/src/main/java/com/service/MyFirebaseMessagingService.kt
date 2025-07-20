package com.service

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.util.Log
import androidx.core.app.NotificationCompat
import com.domain.model.PersonModel
import com.domain.notification.HandlePersonNotificationUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val handlePersonNotificationUseCase: HandlePersonNotificationUseCase by inject()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", "Message received: ${remoteMessage.notification?.body}")


        if(remoteMessage.data.isNotEmpty()) {
            val name = remoteMessage.data["name"]
            val age = remoteMessage.data["age"]
            val latitude = remoteMessage.data["latitude"]
            val amount = remoteMessage.data["amount"]
            val isEnabled = remoteMessage.data["isEnabled"]
            val id = remoteMessage.data["id"]

            if (name != null
                && age != null
                && latitude != null
                && amount != null
                && isEnabled != null
                && id != null) {
                val person = PersonModel(
                    name,
                    age.toInt(),
                    latitude.toFloat(),
                    amount.toDouble(),
                    isEnabled.toBoolean(),
                    id.toLong()
                )

                CoroutineScope(Dispatchers.IO).launch {
                    handlePersonNotificationUseCase(person)
                }
            }
        }
    }

}
