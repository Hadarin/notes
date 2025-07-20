package com.example.myapplication.sandbox.async

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun test11(): Unit = runBlocking {

    val job = launch {
        println("First coroutine started")
        delay(5000)
        println("Hello World")
    }


    launch {
        println("Stopping first coroutine")
        delay(2000)
        //job.cancel()
    }


    val deffered1 = async {
        delay(5000)
        10
    }

    val deffered2 = async {
        delay(5000)
        8
    }

    deffered1.cancel()

    val result = deffered2.await()
    println("Result is : $result")

}

/*
  * Complete subscription with shared flow
  * Make the same with Channel
  *
  *
  * combine()
  * .merge()
  * .flatMapLatest()
  * .flatMapConcat()
  * .flatMapMerge()
  * .zip()
  * .last()
  * .first()
  * .lastOrNull()
  * .filter()
  *
  *
  *
  * Combine StateFlow, SharedFlow, Channel, Flow in one point and get all data from all of them in one Flow
  *
  * */

fun main(): Unit = runBlocking {

    val stringFlow = flow {
        emit("test1")
        delay(1000)
        emit("test2")
    }
    val integerFlow = flowOf(1, 2, 3)
    val mutableStateFlow = MutableStateFlow(0)
    val sharedFlow = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val channel = Channel<String>()

    launch {

        for ( i in 1..5) {
            println("Send into channel String $i")
            channel.send("String in channel $i")
    }

    }

    launch {
        for (value in channel) {
            println("Received from channel $value")
        }
    }

    val sharedFlowJob = launch {
        sharedFlow.collect {
            println("SharedFlow new value is $it")

        }
    }

    launch {
        integerFlow.collect {
            println("Collector 1 value: $it ")
        }
    }

    launch {
        integerFlow.collect {
            println("Collector 2 value: $it")
        }
    }

    launch {
        delay(1000)
        sharedFlow.emit("shared flow value 1")
        delay(1000)
        sharedFlow.emit("shared flow value 2")
        delay(1000)
        sharedFlow.emit("shared flow value 3")
    }

    val job = launch {
        mutableStateFlow.collect {
            println("mutableStateFlow Value is $it")
        }
        println("Test")
    }

    launch {
        delay(2000)
        mutableStateFlow.emit(1)
        delay(1000)
        mutableStateFlow.emit(5)
        delay(1000)
        job.cancel()
    }

    merge(stringFlow, integerFlow, mutableStateFlow, sharedFlow)
        .collect { println("MERGED VALUE IS $it") }

}