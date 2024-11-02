plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {

    // Coroutines
    implementation(libs.coroutines.core.jvm)

    // Paging3
    implementation(libs.paging.common)

    // Internal dependencies
    implementation(project(":core:model"))
}