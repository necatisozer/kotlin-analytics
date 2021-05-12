package com.necatisozer.analytics.core.event

public interface CustomEvent : Event {
    public val name: String
    public val parameters: Map<String, Any> get() = emptyMap()
}
