package com.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.domain.model.NoteModel

@Composable
fun NoteList(noteModelEntities: List<NoteModel>,
             onDelete: (NoteModel) -> Unit,
             onEdit: (NoteModel) -> Unit) {
    LazyColumn {
        items(noteModelEntities) { note ->
            NoteItem(note, onDelete, onEdit)
        }
    }
}

@Composable
fun NoteItem(noteModelEntity: NoteModel,
             onDelete: (NoteModel) -> Unit,
             onEdit: (NoteModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = noteModelEntity.title, style = MaterialTheme.typography.titleLarge)
            Text(text = noteModelEntity.content)
            Row {
                IconButton(onClick = { onEdit(noteModelEntity) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Note")
                }
                IconButton(onClick = { onDelete(noteModelEntity) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Note")
                }
            }
        }
    }
}