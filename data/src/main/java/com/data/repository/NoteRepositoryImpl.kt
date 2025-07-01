package com.data.repository

import com.data.dao.NoteDao
import com.data.mapper.toDomainModel
import com.data.mapper.toEntity
import com.domain.model.NoteModel
import com.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
     suspend fun getNoteById(id: Int) = dao.findNoteById(id)

     override fun getAllNotes(): Flow<List<NoteModel>> {
          return dao.getAllNotes().map { entities ->
               entities.map { it.toDomainModel() }
          }
     }
     override suspend fun addNote(noteModel: NoteModel) {
          dao.insertNote(noteModel.toEntity())
     }

     override suspend fun deleteNote(noteModel: NoteModel) {
          dao.deleteNote(noteModel.toEntity())
     }
}