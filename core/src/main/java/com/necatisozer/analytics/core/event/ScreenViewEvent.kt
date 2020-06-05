package com.necatisozer.analytics.core.event

interface ScreenViewEvent : Event {
    val name: String
    val className: String? get() = null
}
