package com.example.myapplication.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.presentation.screen.home.NoteViewModel

@Composable
fun EditNoteScreen(
    noteId: Int,
    navController: NavController
) {
    val noteViewModel: NoteViewModel = hiltViewModel()

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