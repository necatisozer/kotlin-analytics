package com.necatisozer.analytics.kotlin.event

interface UserProperties : Event {
    val properties: Map<String, Any>
}