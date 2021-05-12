package com.necatisozer.analytics.core.exception

import com.necatisozer.analytics.core.AnalyticsTracker
import com.necatisozer.analytics.core.event.Event

public class EventNotTrackedException(tracker: AnalyticsTracker, event: Event, cause: Throwable) :
    RuntimeException("${tracker.name} couldn't track $event", cause)
