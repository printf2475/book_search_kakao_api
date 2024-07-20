import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.library")
    id("assignment.android.compose")
    id("kotlin-parcelize")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    setNamespace("core.navigation")
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.common.ktx)
}