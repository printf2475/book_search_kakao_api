import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.library")
    id("assignment.android.hilt")
}


android {
    setNamespace("core.domain")
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.paging.common)
}