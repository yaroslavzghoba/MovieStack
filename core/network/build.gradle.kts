import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization.android)
    id("kotlin-kapt")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.yaroslavzghoba.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // Get the API keys from local.properties
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField(
            type = "String",
            name = "TMDB_ACCESS_TOKEN",
            value = "\"${properties.getProperty("TMDB_ACCESS_TOKEN")}\"",
        )
        buildConfigField(
            type = "String",
            name = "TMDB_API_KEYS",
            value = "\"${properties.getProperty("TMDB_API_KEYS")}\"",
        )
    }

    // Generate BuildConfig class
    buildFeatures {
        buildConfig = true
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

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    // Retrofit & Serialization
    implementation(libs.retrofit)
    implementation(libs.serialization.json)
    implementation(libs.serialization.converter)
    implementation(libs.okhttp)

    // Hilt dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Internal dependencies
    implementation(project(":core:model"))
}