package com.example.myapplication.sandbox.async.flow_methods

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

sealed class TaggedEvent {
    data class FromA(val value: Int) : TaggedEvent()
    data class FromB(val value: String) : TaggedEvent()
}

fun main() : Unit = runBlocking {

    val flowA = flowOf(1, 2, 3).map { TaggedEvent.FromA(it) }
    val flowB = flowOf("x", "y").map { TaggedEvent.FromB(it) }

    val merged = merge(flowA, flowB)

    merged.collect { event ->
        when (event) {
            is TaggedEvent.FromA -> println("A emitted: ${event.value}")
            is TaggedEvent.FromB -> println("B emitted: ${event.value}")
        }


    }
}