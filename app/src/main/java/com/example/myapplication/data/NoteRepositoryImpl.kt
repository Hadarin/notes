package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
     suspend fun getNoteById(id: Int) = dao.findNoteById(id)
     override fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()
     override suspend fun addNote(note: Note) = dao.insertNote(note)
     override suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}

