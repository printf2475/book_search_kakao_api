import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.feature")
}

android{
    setNamespace("feature.search")
}

dependencies {
    implementation(libs.paging.compose)
}