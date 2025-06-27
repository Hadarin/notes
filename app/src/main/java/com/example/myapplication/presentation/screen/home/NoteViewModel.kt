package com.example.myapplication.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Note
import com.example.myapplication.data.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(NoteFormState())
    val state: StateFlow<NoteFormState> = _state.asStateFlow()

    val notes: StateFlow<List<Note>> = repository.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    private val _editNoteState = MutableStateFlow<Note?>(null)
    val editNoteState: StateFlow<Note?> = _editNoteState

    fun updateTitle(newTitle: String) {
        _state.value = _state.value.copy(title = newTitle)
    }

    fun updateContent(newContent: String) {
        _state.value = _state.value.copy(content = newContent)
    }

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.addNote(Note(title = title, content = content))
        }
    }

    fun loadNoteById(id: Int) {
        viewModelScope.launch {
            repository.getNoteById(id)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

}