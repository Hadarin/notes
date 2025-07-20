package com.example.myapplication.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.domain.model.PersonModel
import com.domain.notification.NotificationHandler
import com.example.myapplication.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppNotificationHandlerImpl(
    private val context: Context
) : NotificationHandler {

    override suspend fun handlePersonNotification(person: PersonModel) {
        withContext(Dispatchers.Main) {
            showPersonNotification(person)
        }
    }

    private fun showPersonNotification(person: PersonModel) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create channel for Android 8.0+
        val channelId = "person_notification_channel"
        val channel = NotificationChannel(
            channelId,
            "Person Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        // Create intent with person data
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("person_object", person)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            person.id.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Person Details Received")
            .setContentText("Tap to view details for ${person.name}")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(person.id.toInt(), notification)
    }
}