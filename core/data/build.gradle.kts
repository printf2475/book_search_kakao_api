import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.library")
    id("assignment.android.hilt")
    id("kotlinx-serialization")
}

android {
    setNamespace("core.data")

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://dapi.kakao.com/\"")
        buildConfigField("String", "KAKAO_KEY", "\"36b434e688567d95a6555b9dfc1e4cba\"")
    }

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.database)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
}