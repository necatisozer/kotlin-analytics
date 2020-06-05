package com.necatisozer.analytics.android.event

import android.app.Activity
import com.necatisozer.analytics.core.event.ScreenViewEvent

interface AndroidScreenViewEvent : ScreenViewEvent {
    val activity: Activity
}
