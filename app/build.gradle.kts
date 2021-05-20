plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.ID
        versionCode = AppCoordinates.VERSION_CODE
        versionName = AppCoordinates.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(project(":android"))
    implementation(project(":tracker-firebase"))

    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.coreKtx)

    testImplementation(libs.junitJunit)

    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.androidxTestRules)
    androidTestImplementation(libs.espressoCore)
}
