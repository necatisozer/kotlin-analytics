package com.necatisozer.analytics.kotlin.event

interface UserProperty : UserProperties {
    val name: String
    val value: Any

    override val properties: Map<String, Any>
        get() = mapOf(name to value)
}
