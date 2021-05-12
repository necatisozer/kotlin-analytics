package com.necatisozer.analytics.android

import android.content.Context
import com.necatisozer.analytics.core.AnalyticsTracker

public interface AndroidAnalyticsTracker : AnalyticsTracker {
    public val context: Context
}
