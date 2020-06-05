package com.necatisozer.analytics.core.event

interface CustomEvent : Event {
    val name: String
    val parameters: Map<String, Any> get() = emptyMap()
}
