package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
}