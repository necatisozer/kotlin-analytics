package com.necatisozer.analytics.core.event

public interface UserProperties : Event {
    public val properties: Map<String, Any>
}
