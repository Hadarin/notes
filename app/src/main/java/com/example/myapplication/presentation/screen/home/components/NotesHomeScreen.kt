package com.example.myapplication.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.screen.home.NoteViewModel
import com.example.myapplication.presentation.screen.home.Route

@Composable
fun HomeScreen(navController: NavHostController) {

    val noteViewModel: NoteViewModel = hiltViewModel()

    MaterialTheme {
        Column {
            AddNoteForm(noteViewModel)
            val notes by noteViewModel.notes.collectAsState()
            NoteList(notes,
                onDelete = { noteViewModel.deleteNote(it) },
                onEdit = { note -> navController.navigate(Route.EditNote(note.id)) }
            )
            Row {
                Button(
                    onClick = { navController.navigate(Route.TestOne) }
                ) {
                    Text("Screen One")
                }
                Button(
                    onClick = { navController.navigate(Route.TestTwo) }
                ) {
                    Text("Screen Two")
                }
                Button(
                    onClick = { navController.navigate(Route.TestThree) }
                ) {
                    Text("Screen Three")
                }
            }
        }
    }
}