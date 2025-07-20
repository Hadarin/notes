package com.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.presentation.navigation.AppRouter
import com.presentation.navigation.NavigationEffect
import com.presentation.screen.geolocation.GoogleApiLocationScreen
import com.presentation.screen.socket.ChatScreen
import org.koin.compose.koinInject

@Composable
fun TestScreenThree() {

    val router: AppRouter = koinInject()

    MaterialTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )
        {
            ChatScreen()
            GoogleApiLocationScreen()
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { router.navigateTo(NavigationEffect.TestTwo) }
            ) {
                Text("Back")
            }
        }
    }
}