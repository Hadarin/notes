package com.example.myapplication.sandbox.async

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun coldFlow(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun main() = runBlocking {
    println("Collector 1 is starting")
    coldFlow().collect { value ->
        println("Collector 1 received: $value")
    }

    println("\nCollector 2 is starting")
    coldFlow().collect { value ->
        println("Collector 2 received: $value")
    }
}