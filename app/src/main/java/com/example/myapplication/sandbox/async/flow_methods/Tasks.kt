package com.example.myapplication.sandbox.async.flow_methods

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {

//map each to squares
    val flow1 = flowOf(1, 2, 3, 4, 5)
    flow1
        .map { it * it }
        .collect { println("$it") }

//Filter only even numbers from a Flow of integers from 1 to 10.
    val flow2 = (1..10).asFlow()
    flow2.filter { it -> it % 2 == 0 }
        .collect { println("$it") }

    //Exercise 4: Flatten Nested Flows
    //Goal: Given a Flow of integers, for each number emit a Flow of that number repeated n times.
    val flow5 = flowOf(2, 3)
        .flatMapConcat { it ->
            flow {
                for (i in 1..it) {
                    emit(it)
                }
            }
        }
        .collect { println(it) }

//Exercise 5: Cancel Previous and Start New. Search simulator
    val search = flowOf("a", "ab", "abc")
        .filter { query -> query.length > 1 }
        .onEach { delay(100) }
        .flatMapLatest { query ->
            flow {
                println("Start searching: $query")
                delay(200) // Simulate long search
                emit("Result for: $query")
            }
        }
        .collect { println(it) }

    //Exercise 7 merge two flows

    val flowToMerge1 = flow {
        emit("A")
        delay(100)
        emit("B")
    }
    val flowToMerge2 = flow {
        delay(50)
        emit("1")
        delay(100)
        emit("2")
    }

    merge(flowToMerge1, flowToMerge2)
        .onEach { delay(500) }
        .collect { println(it) }

    // Combine Two Flows
    val flow3 = flowOf("A", "B", "C")
    val flow4 = flowOf(1, 2, 3)

    flow3.zip(flow4) { a, b -> "$a$b" }
        .onEach { delay(500) }
        .collect { println(it) }

    println(flow3.first())


    val stateFlow = MutableStateFlow<Int?>(null)

// Collect in UI or ViewModel
    launch {
        stateFlow.collect { value ->
            println("Latest value: $value")
        }
    }

// Somewhere else
    stateFlow.value = 1
    stateFlow.value = 2

}