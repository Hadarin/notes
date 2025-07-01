package com.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.NoteModel
import com.domain.repository.NoteRepository


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel (private val repository: NoteRepository) : ViewModel() {

    private val _state = MutableStateFlow(NoteFormState())
    val state: StateFlow<NoteFormState> = _state.asStateFlow()

    val notes: StateFlow<List<NoteModel>> = repository.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    private val _editNoteModelEntityState = MutableStateFlow<NoteModel?>(null)
    val editNoteModelEntityState: StateFlow<NoteModel?> = _editNoteModelEntityState

    fun updateTitle(newTitle: String) {
        _state.value = _state.value.copy(title = newTitle)
    }

    fun updateContent(newContent: String) {
        _state.value = _state.value.copy(content = newContent)
    }

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.addNote(NoteModel(title = title, content = content))
        }
    }

    fun deleteNote(noteModelEntity: NoteModel) {
        viewModelScope.launch {
            repository.deleteNote(noteModelEntity)
        }
    }

}