package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) {
     suspend fun getNoteById(id: Int) = dao.findNoteById(id)
     fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()
     suspend fun addNote(note: Note) = dao.insertNote(note)
     suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}

