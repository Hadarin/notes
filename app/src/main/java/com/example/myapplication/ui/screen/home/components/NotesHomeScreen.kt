package com.example.myapplication.ui.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.myapplication.ui.screen.home.NoteViewModel
import com.example.myapplication.ui.screen.home.Router

import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {

    val noteViewModel: NoteViewModel = koinViewModel()

    MaterialTheme {
        Column {
            AddNoteForm(noteViewModel)
            val notes by noteViewModel.notes.collectAsState()
            NoteList(notes,
                onDelete = { noteViewModel.deleteNote(it) },
                onEdit = { note -> navController.navigate(Router.EditNote.createRouteByNoteId(note.id)) }
            )
            Row {
                Button(
                    onClick = { navController.navigate("testOne") }
                ) {
                    Text("Screen One")
                }
                Button(
                    onClick = { navController.navigate("testTwo") }
                ) {
                    Text("Screen Two")
                }
                Button(
                    onClick = { navController.navigate("testThree") }
                ) {
                    Text("Screen Three")
                }
            }
        }
    }
}