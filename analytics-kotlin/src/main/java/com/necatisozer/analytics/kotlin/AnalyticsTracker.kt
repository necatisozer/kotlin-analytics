package com.necatisozer.analytics.kotlin

import com.necatisozer.analytics.kotlin.event.Event

interface AnalyticsTracker {
    val name: String
    fun track(event: Event)
}