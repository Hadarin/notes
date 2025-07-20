package com.data.repository

import android.util.Log
import com.domain.repository.SocketRepository
import io.socket.client.IO
import io.socket.client.Socket

class SocketRepositoryImpl : SocketRepository {

    private val socket: Socket = IO.socket("http://10.0.2.2:3000")

    override fun connect() {
        socket.on(Socket.EVENT_CONNECT) {
            Log.d("SocketRepo", "✅ Connected to socket")
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            Log.e("SocketRepo", "❌ Connect error: ${args.joinToString()}")
        }
        socket.connect()
    }

    override fun disconnect() {
        socket.disconnect()
    }

    override fun sendMessage(message: String) {
        socket.emit("chat_message", message)
    }

    override fun observeMessages(onMessageReceived: (String) -> Unit) {
        socket.on("chat_message") { args ->
            if (args.isNotEmpty()) {
                val msg = args[0] as? String
                Log.d("SocketRepo", "Received: $msg")
                msg?.let { onMessageReceived(it) }
            }
        }
    }
}