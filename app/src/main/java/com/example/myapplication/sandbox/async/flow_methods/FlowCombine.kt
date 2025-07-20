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

    // Launch channel producer
    launch {
        for (i in 1..5) {
            println("Send into channel String $i")
            channel.send("String in channel $i")
        }
    }

    // Launch channel consumer
    launch {
        for (value in channel) {
            println("Received from channel $value")
        }
    }

        /*
    launch {
        delay(500) // Give some time for collectors to start
        sharedFlow.emit("SharedFlow data 1")
        delay(1000)
        sharedFlow.emit("SharedFlow data 2")
    }

         */

    // Original merge (this works fine)
    val bigFlow = merge(
        stringFlow,
        integerFlow,
        mutableStateFlow,
        sharedFlow,
        channel.receiveAsFlow()
    )

    launch {
        bigFlow.collect { println("COLLECTOR 1 - Here is a data $it") }
    }

    // FIX 2: Use separate channel for combine to avoid conflicts
    val channelForCombine = Channel<String>()

    launch {
        for (i in 1..3) {
            delay(200)
            channelForCombine.send("Channel for combine $i")
        }
        channelForCombine.close()
    }

    val combinedBigFlow = combine(
        stringFlow,
        integerFlow,
        sharedFlow,
        channelForCombine.receiveAsFlow()
    ) { stringFlowData, integerFlowData, sharedFlowData, channelData ->
        CombinedData(
            stringFlow = stringFlowData,
            integerFlow = integerFlowData,
            sharedStringFlow = sharedFlowData,
            channelFlow = channelData
        )
    }

    launch {
        delay(100) // Start collection early
        combinedBigFlow.collect {
            println("COMBINED: ${it.stringFlow} + ${it.integerFlow} + ${it.sharedStringFlow} + ${it.channelFlow}")
        }
    }

    delay(10000) // Give time for all operations
}

data class CombinedData(
    val stringFlow: String,
    val integerFlow: Int,
    val sharedStringFlow: String,
    val channelFlow: String
)