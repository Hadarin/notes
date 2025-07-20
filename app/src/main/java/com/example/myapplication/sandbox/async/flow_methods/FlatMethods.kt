package com.example.myapplication.sandbox.async.flow_methods

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main() : Unit = runBlocking {

    launch {
        flowOf(1, 2, 3)
            .onEach { delay(100) } // emit every 100ms
            .flatMapLatest { id -> // Try changing to flatMapConcat or flatMapMerge
                flow {
                    emit("Start $id")
                    delay(300)
                    emit("End $id")
                }
            }
            .collect { println(it) }
    }

}