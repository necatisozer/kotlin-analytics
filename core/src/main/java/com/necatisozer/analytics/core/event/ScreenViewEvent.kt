package com.necatisozer.analytics.core.event

public interface ScreenViewEvent : Event {
    public val name: String
    public val className: String? get() = null
}
