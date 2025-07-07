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
import org.koin.compose.koinInject

@Composable
fun TestScreenThree() {

    val router: AppRouter = koinInject()

    MaterialTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        )
        {
            Text(
                text = "Test screen 3 text",
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { router.navigateTo(NavigationEffect.TestTwo) }
            ) {
                Text("Back")
            }
        }
    }
}