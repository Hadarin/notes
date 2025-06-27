package com.example.myapplication.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.navigation.AppRouter
import com.example.myapplication.presentation.navigation.NavigationEffect
import org.koin.compose.koinInject

@Composable
fun EditNoteScreen(
    noteId: Int,
)
{
    val router: AppRouter = koinInject()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Edit Note with ID: $noteId")

        Button(
            onClick = { router.navigateTo(NavigationEffect.Back) }
        ) {
            Text("Back")
        }
    }
}