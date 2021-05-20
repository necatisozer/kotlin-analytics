version = AnalyticsKotlinCoordinates.VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    publish
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    testImplementation(libs.junitJunit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
