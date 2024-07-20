import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.feature")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("feature.detail")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}