package com.necatisozer.analytics.core

import com.necatisozer.analytics.core.event.Event

public interface AnalyticsTracker {
    public val name: String
    public fun track(event: Event)
}
