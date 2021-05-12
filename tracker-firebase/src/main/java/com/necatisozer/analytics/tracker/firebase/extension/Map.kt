package com.necatisozer.analytics.tracker.firebase.extension

import android.os.Bundle

internal fun Map<String, Any>.toBundle() = Bundle().apply {
    forEach { put(it.key, it.value) }
}
