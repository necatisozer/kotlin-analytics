package com.necatisozer.analytics.tracker.firebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.necatisozer.analytics.android.AndroidAnalyticsTracker
import com.necatisozer.analytics.android.event.AndroidScreenViewEvent
import com.necatisozer.analytics.core.event.CustomEvent
import com.necatisozer.analytics.core.event.Event
import com.necatisozer.analytics.core.event.UserProperties
import com.necatisozer.analytics.tracker.firebase.extension.toBundle

open class FirebaseTracker(override val context: Context) : AndroidAnalyticsTracker {
    override val name = "Firebase Analytics"

    private val firebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun track(event: Event) {
        when (event) {
            is CustomEvent -> trackCustomEvent(event)
            is AndroidScreenViewEvent -> trackScreenViewEvent(event)
            is UserProperties -> setUserProperties(event)
        }
    }

    protected fun trackCustomEvent(event: CustomEvent) {
        firebaseAnalytics.logEvent(event.name, event.parameters.toBundle())
    }

    protected fun trackScreenViewEvent(event: AndroidScreenViewEvent) {
        firebaseAnalytics.setCurrentScreen(event.activity, event.name, event.className)
    }

    protected fun setUserProperties(event: UserProperties) {
        event.properties.forEach {
            firebaseAnalytics.setUserProperty(it.key, it.value.toString())
        }
    }
}
