package com.soarclient.skid.events

abstract class EventArgument protected constructor() {
    private var isCancelled = false
        private set

    fun cancel() {
        isCancelled = true
    }

    abstract fun call(listener: EventListener)
}