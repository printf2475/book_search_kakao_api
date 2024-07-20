import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.library")
    id("assignment.android.room")
    id("assignment.android.hilt")
}

android {
    setNamespace("core.database")
}

dependencies {
    implementation(projects.core.model)
}

