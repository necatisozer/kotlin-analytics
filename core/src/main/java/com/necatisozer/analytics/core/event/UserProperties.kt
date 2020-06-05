package com.necatisozer.analytics.core.event

interface UserProperties : Event {
    val properties: Map<String, Any>
}
