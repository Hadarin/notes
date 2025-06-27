package com.example.myapplication.presentation.screen.home.components

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
import com.example.myapplication.presentation.navigation.AppRouter
import com.example.myapplication.presentation.navigation.NavigationEffect
import org.koin.compose.koinInject

@Composable
fun TestScreenTwo() {

    val router: AppRouter = koinInject()

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        ) {
            Text(
                text = "Test screen 2 text",
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { router.navigateTo(NavigationEffect.TestOne) }
            ) {
                Text("Back")
            }
            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { router.navigateTo(NavigationEffect.TestThree) }
            ) {
                Text("Screen 3")
            }
        }
    }
}