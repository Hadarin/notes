package com.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.presentation.navigation.AppRouter
import com.presentation.navigation.NavigationEffect
import com.presentation.screen.home.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen() {

    val noteViewModel: NoteViewModel = koinViewModel()
    val router: AppRouter = koinInject()

    MaterialTheme {
        Column {
            AddNoteForm(noteViewModel)
            val notes by noteViewModel.notes.collectAsState()
            NoteList(notes,
                onDelete = { noteViewModel.deleteNote(it) },
                onEdit = { note -> router.navigateTo(NavigationEffect.EditNote(note.id)) }
            )
            Row {
                Button(
                    onClick = { router.navigateTo(NavigationEffect.TestOne) }
                ) {
                    Text("Screen One")
                }
                Button(
                    onClick = { router.navigateTo(NavigationEffect.TestTwo) }
                ) {
                    Text("Screen Two")
                }
                Button(
                    onClick = { router.navigateTo(NavigationEffect.TestThree) }
                ) {
                    Text("Screen Three")
                }
            }
        }
    }
}