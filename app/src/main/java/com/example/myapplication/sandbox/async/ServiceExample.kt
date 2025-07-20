package com.example.myapplication.sandbox.async

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ServiceExample {

    //creating a cutsom coroutine
    val customContext = Dispatchers.Default + CoroutineName("MyCustomCoroutine")

    // Creating a custom scope
    private val serviceScope = CoroutineScope(customContext)

    fun startWork() {
        serviceScope.launch {
            println("Starting background work...")
            delay(1000)
            println("Background work completed")
        }
    }

    fun cleanup() {
        serviceScope.cancel() // Cancel all coroutines in this scope
    }
}

fun main() = runBlocking {
    val service = ServiceExample()
    service.startWork()

    delay(500)
    service.cleanup()
    println("Service cleaned up")

}