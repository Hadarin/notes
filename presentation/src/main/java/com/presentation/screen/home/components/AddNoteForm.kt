package com.presentation.screen.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.presentation.screen.home.NoteViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddNoteForm(viewModel: NoteViewModel) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

        Column(
            modifier = Modifier.padding(80.dp)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.updateTitle(it) },
                label = { Text("Name") }
            )
            OutlinedTextField(
                value = state.content,
                onValueChange = { viewModel.updateContent(it) },
                label = { Text("Content") }
            )
            Button(onClick = {
                if (state.title.isNotBlank() && state.content.isNotBlank()) {
                    viewModel.addNote(state.title, state.content)
                }
            }) {
                Text("Add Note")
            }
        }
}