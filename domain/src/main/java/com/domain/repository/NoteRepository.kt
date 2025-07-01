package com.domain.repository


import com.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<NoteModel>>
    suspend fun addNote(noteModel: NoteModel)
    suspend fun deleteNote(noteModel: NoteModel)
}