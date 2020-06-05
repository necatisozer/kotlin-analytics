package com.necatisozer.analytics.android

import android.content.Context
import com.necatisozer.analytics.kotlin.AnalyticsTracker

interface AndroidAnalyticsTracker : AnalyticsTracker {
    val context: Context
}