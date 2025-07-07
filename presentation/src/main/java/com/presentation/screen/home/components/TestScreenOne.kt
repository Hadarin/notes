package com.presentation.screen.home.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.presentation.navigation.AppRouter
import com.presentation.navigation.NavigationEffect
import com.service.TestService
import org.koin.compose.koinInject

@Composable
fun TestScreenOne() {

    val router: AppRouter = koinInject()
    val context = LocalContext.current

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = "Test screen 1 text",
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { router.navigateTo(NavigationEffect.Back)
                }
            ) {
                Text("Back")
            }
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                val intent = Intent(context, TestService::class.java)
                context.startService(intent)
            }
                ) {
                Text("Start Service")
            }
            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { router.navigateTo(NavigationEffect.TestTwo)
                }
            ) {
                Text("Screen 2")
            }
        }
    }
}
