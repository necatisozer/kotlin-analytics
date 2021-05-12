package com.necatisozer.analytics.core.event

public interface UserProperty : UserProperties {
    public val name: String
    public val value: Any

    override val properties: Map<String, Any>
        get() = mapOf(name to value)
}
