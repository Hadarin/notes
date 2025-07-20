package com.presentation.screen.socket

import androidx.lifecycle.ViewModel
import com.domain.repository.SocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SocketViewModel(
    private val socketRepository: SocketRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages

    init {
        socketRepository.connect()
        socketRepository.observeMessages { message ->
            _messages.update { it + message }
        }
    }

    fun sendMessage(message: String) {
        socketRepository.sendMessage(message)
    }

    override fun onCleared() {
        super.onCleared()
        socketRepository.disconnect()
    }
}