plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //kapt
    id("kotlin-kapt")
    //dagger hilt
    id("dagger.hilt.android.plugin")
    //parcelize
    id("kotlin-parcelize")
}

android {
    namespace = "com.bhushan.tastebuds"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bhushan.tastebuds"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //view binding enabled
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //test implementation library
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)
    testImplementation(libs.mockito.mockito.core)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)

    // parcelize, appcompact
    implementation(libs.androidx.appcompat.v131)
    implementation(libs.kotlin.stdlib)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Activity KTX for viewModels()
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    //glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    // Lottie Animation
    implementation(libs.lottie)

    // Chucker
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)
}

kapt {
    correctErrorTypes = true
}