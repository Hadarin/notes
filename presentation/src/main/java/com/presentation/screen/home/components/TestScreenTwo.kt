package com.presentation.screen.home.components

import android.util.Log
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
import com.presentation.screen.home.components.camera.CameraButton
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
            CameraButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onCameraResult = { uri ->
                    if (uri != null) {
                        // Handle the captured photo URI
                        Log.d("Camera", "Photo saved at: $uri")
                    } else {
                        // Handle case where photo wasn't taken
                        Log.d("Camera", "Photo not taken")
                    }
                }
            )
            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { router.navigateTo(NavigationEffect.TestThree) }
            ) {
                Text("Screen 3")
            }
        }
    }
}