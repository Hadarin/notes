package com.presentation.screen.home.components

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.service.TestService
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.service.MyForegroundService
import com.service.ServiceStarterWorker

@Composable
fun StartTheService () {
    val context = LocalContext.current

    Button(
        onClick = {
            // This approach is more reliable
            val workRequest = OneTimeWorkRequestBuilder<ServiceStarterWorker>()
                .setExpedited(androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()

            WorkManager.getInstance(context).enqueue(workRequest)
        }
    ) {
        Text("Start Background Work (WorkManager)")
    }

}