package com.domain.repository

interface SocketRepository {
    fun connect()
    fun disconnect()
    fun sendMessage(message: String)
    fun observeMessages(onMessageReceived: (String) -> Unit)
}