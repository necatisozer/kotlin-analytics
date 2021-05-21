pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.library") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
        id("de.fayard.refreshVersions") version "0.10.0"
}

rootProject.name = ("kotlin-analytics")

include(
    "core",
    "android",
    "tracker-firebase",
    "app"
)

enableFeaturePreview("VERSION_CATALOGS")