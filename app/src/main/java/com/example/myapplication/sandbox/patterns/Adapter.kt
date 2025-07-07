package com.example.myapplication.sandbox.patterns

class LegacyLogger {
    fun logMessage(msg: String) {
        println("Legacy log: $msg")
    }
}

interface Logger {
    fun log(msg: String)
}

class LoggerAdapter(private val legacyLogger: LegacyLogger) : Logger {
    override fun log(msg: String) {
        legacyLogger.logMessage(msg)
    }
}

fun main() {
    val logger: Logger = LoggerAdapter(LegacyLogger())
    logger.log("Adapted log message here")
}