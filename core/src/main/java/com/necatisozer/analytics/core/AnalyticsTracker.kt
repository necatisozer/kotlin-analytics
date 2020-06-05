package com.necatisozer.analytics.core

import com.necatisozer.analytics.core.event.Event

interface AnalyticsTracker {
    val name: String
    fun track(event: Event)
}
