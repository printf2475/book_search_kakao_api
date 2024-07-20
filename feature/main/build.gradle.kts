import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.feature")
}

android {
    setNamespace("feature.main")
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.search)
    implementation(projects.feature.favorite)
    implementation(projects.feature.detail)
}