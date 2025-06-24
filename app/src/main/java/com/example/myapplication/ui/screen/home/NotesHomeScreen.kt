package com.example.myapplication.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import com.example.myapplication.ui.screen.home.components.AddNoteForm
import com.example.myapplication.ui.screen.home.components.NoteList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {

    val noteViewModel: NoteViewModel = koinViewModel()

    MaterialTheme {
        Column {
            AddNoteForm(noteViewModel)
            val notes by noteViewModel.notes.collectAsState()
            NoteList(notes,
                onDelete = { noteViewModel.deleteNote(it) },
                onEdit = { p -> println("Edit should be here!!!") })
        }
    }
}