package com.necatisozer.analytics.android.event

import android.app.Activity
import com.necatisozer.analytics.kotlin.event.ScreenViewEvent

interface AndroidScreenViewEvent : ScreenViewEvent {
    val activity: Activity
    val className: String? get() = null
}