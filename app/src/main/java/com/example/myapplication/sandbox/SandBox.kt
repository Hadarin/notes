package com.example.myapplication.sandbox

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//6/23/205
// Flow, Channel, StateFlow, SharedFlow
// suspend func, CoroutineContext, Job, CoroutineScope
// launch, async


//Kotlin by delegates feature

//Task: Create HomeScreen composable func, must have HomeViewModel
//HomeScreen subscribes on HomeViewModelState (data class HomeState)
//HomeScreen uses public ViewModel's func processIntent (sealed interface HomeIntent)


//DI Koin

//6/24/205
//Create second screen for each item of the list of notes (navigation tutor)
//View model with states without compose remember
//Implement Hilt instead of Koin

//6/27/2025
//Serialization under the hood
//Parselable under the hood
//Use case in domain layer for Repository

// separate classes data-domain-presentation
// presentation should know about domain,
// data should know about domain,
// domain shouldn't know anyone,

//8/1/2025 - manifest tags, create service in manifest


//7/72025 - Worker, API requests from android app (Gson, retrofit, okhttp) and show the data on screen

fun simple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(2000)
        emit(i)
    }
}

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun createFlow(): Flow<Int> = flow {
    repeat(3) {
        delay(1000) // Simulate work
        emit(it)
    }
}.flowOn(Dispatchers.Default) // Flow will emit from background thread

fun main() {

    for (i in 1..3) {
        Thread.sleep(2000)
        println(i)
    }

}

