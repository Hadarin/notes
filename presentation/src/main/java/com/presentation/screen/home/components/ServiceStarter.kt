package com.presentation.screen.home.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.service.TestService
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartTheService (context: Context,
                     modifier: Modifier) {

    Button(onClick = {
        val intent = Intent(context, TestService::class.java)
        context.startService(intent)
    },
        modifier = Modifier.padding(16.dp)) {
        Text("Start Service")
    }

}