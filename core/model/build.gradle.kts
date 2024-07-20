import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.library")
    id("assignment.android.room")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}


android {
    setNamespace("core.model")
}

dependencies{
    implementation(libs.kotlinx.serialization.json)
}
