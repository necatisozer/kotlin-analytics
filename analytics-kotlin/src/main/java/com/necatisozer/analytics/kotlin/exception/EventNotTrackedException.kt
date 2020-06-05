package com.necatisozer.analytics.kotlin.exception

import com.necatisozer.analytics.kotlin.AnalyticsTracker
import com.necatisozer.analytics.kotlin.event.Event

class EventNotTrackedException(tracker: AnalyticsTracker, event: Event, cause: Throwable) :
    RuntimeException("${tracker.name} couldn't track $event", cause)
