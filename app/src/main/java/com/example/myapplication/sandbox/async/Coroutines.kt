package com.example.myapplication.sandbox.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun test(): Unit = runBlocking {

    println("Context:  $coroutineContext")
    println("Job: $coroutineContext[Job]")
    println("Dispatcher: $coroutineContext[ContinuationInterceptor]")

    val customContext = Dispatchers.IO + SupervisorJob()

    launch(customContext) {
        println("Custom context $coroutineContext")
        delay(1000)
        println("Work completed on custom context")
    }

}

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Working: $i")
                delay(100)
            }
        } catch (e: CancellationException) {
            println("Job was cancelled")
        }
    }

    println("Job state - Active: ${job.isActive}")
    println("Job state - Completed: ${job.isCompleted}")
    println("Job state - Cancelled: ${job.isCancelled}")

    delay(300)
    job.cancel()

    println("After cancellation:")
    println("Job state - Active: ${job.isActive}")
    println("Job state - Completed: ${job.isCompleted}")
    println("Job state - Cancelled: ${job.isCancelled}")
}