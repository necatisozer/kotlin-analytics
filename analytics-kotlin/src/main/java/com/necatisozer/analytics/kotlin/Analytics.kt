package com.necatisozer.analytics.kotlin

import com.necatisozer.analytics.kotlin.event.Event
import com.necatisozer.analytics.kotlin.exception.EventNotTrackedException

class Analytics(
    var isEnabled: Boolean = true,
    var onError: ((Throwable) -> Unit)? = null
) {
    private val trackerMap = mutableMapOf<String, TrackerData>()

    val enabledTrackers: List<AnalyticsTracker>
        get() = trackerMap.values.mapNotNull { trackerData ->
            trackerData.takeIf { it.isEnabled }?.tracker
        }

    fun registerTracker(id: String, tracker: AnalyticsTracker, isEnabled: Boolean = true) {
        trackerMap[id] = TrackerData(tracker, isEnabled)
    }

    fun isTrackerEnabled(id: String): Boolean? = trackerMap[id]?.isEnabled

    fun setTrackerEnabled(id: String, isEnabled: Boolean = true) {
        trackerMap[id]?.isEnabled = isEnabled
    }

    fun track(vararg events: Event) {
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
