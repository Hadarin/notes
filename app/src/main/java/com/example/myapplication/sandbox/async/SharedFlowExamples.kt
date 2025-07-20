package com.example.myapplication.sandbox.async

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {

    val sharedFlow = MutableSharedFlow<String>()

    // Multiple collectors
    launch {
        sharedFlow.collect { value ->
            println("Collector 1: $value")
        }
    }

    launch {
        sharedFlow.collect { value ->
            println("Collector 2: $value")
        }
    }

    delay(100) // Let collectors start

    // Emit values
    sharedFlow.emit("Message 1")
    sharedFlow.emit("Message 2")

    delay(5000)

    sharedFlow.emit("Message 3")
    sharedFlow.emit("Message 4")

    delay(3000)

    sharedFlow.emit("Message 5")
}