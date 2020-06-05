package com.necatisozer.analytics.kotlin.event

interface CustomEvent : Event {
    val name: String
    val parameters: Map<String, Any> get() = emptyMap()
}
