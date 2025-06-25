package com.example.myapplication.ui.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.screen.home.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditNoteScreen(
    noteId: Int,
    navController: NavController
) {
    val noteViewModel: NoteViewModel = koinViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Edit Note with ID: $noteId")

        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text("Back")
        }
    }
}