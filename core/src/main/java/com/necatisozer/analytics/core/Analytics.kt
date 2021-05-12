package com.necatisozer.analytics.core

import com.necatisozer.analytics.core.event.Event
import com.necatisozer.analytics.core.exception.EventNotTrackedException

public class Analytics(
    public var isEnabled: Boolean = true,
    private var onError: ((Throwable) -> Unit)? = null
) {
    private val trackerMap = mutableMapOf<String, TrackerData>()

    public val enabledTrackers: List<AnalyticsTracker>
        get() = trackerMap.values.mapNotNull { trackerData ->
            trackerData.takeIf { it.isEnabled }?.tracker
        }

    public fun registerTracker(id: String, tracker: AnalyticsTracker, isEnabled: Boolean = true) {
        trackerMap[id] = TrackerData(tracker, isEnabled)
    }

    public fun isTrackerEnabled(id: String): Boolean? = trackerMap[id]?.isEnabled

    public fun setTrackerEnabled(id: String, isEnabled: Boolean = true) {
        trackerMap[id]?.isEnabled = isEnabled
    }

    public fun track(vararg events: Event) {
        if (isEnabled.not()) return

        enabledTrackers.forEach { tracker ->
            events.forEach { event ->
                runCatching {
                    tracker.track(event)
                }.onFailure {
                    onError?.invoke(EventNotTrackedException(tracker, event, it))
                }
            }
        }
    }

    private class TrackerData(
        val tracker: AnalyticsTracker,
        var isEnabled: Boolean = true
    )
}
