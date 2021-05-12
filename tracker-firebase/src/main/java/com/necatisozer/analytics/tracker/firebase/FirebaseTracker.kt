package com.necatisozer.analytics.tracker.firebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.necatisozer.analytics.android.AndroidAnalyticsTracker
import com.necatisozer.analytics.core.event.CustomEvent
import com.necatisozer.analytics.core.event.Event
import com.necatisozer.analytics.core.event.ScreenViewEvent
import com.necatisozer.analytics.core.event.UserProperties
import com.necatisozer.analytics.tracker.firebase.extension.toBundle

open class FirebaseTracker(override val context: Context) : AndroidAnalyticsTracker {
    override val name = "Firebase Analytics"

    private val firebaseAnalytics by lazy { Firebase.analytics }

    override fun track(event: Event) {
        when (event) {
            is CustomEvent -> trackCustomEvent(event)
            is ScreenViewEvent -> trackScreenViewEvent(event)
            is UserProperties -> setUserProperties(event)
        }
    }

    private fun trackCustomEvent(event: CustomEvent) {
        firebaseAnalytics.logEvent(event.name, event.parameters.toBundle())
    }

    private fun trackScreenViewEvent(event: ScreenViewEvent) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, event.name)
            event.className?.let { param(FirebaseAnalytics.Param.SCREEN_CLASS, it) }
        }
    }

    private fun setUserProperties(event: UserProperties) {
        event.properties.forEach {
            firebaseAnalytics.setUserProperty(it.key, it.value.toString())
        }
    }
}
