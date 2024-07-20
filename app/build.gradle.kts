import co.assignment.convention.setNamespace

plugins {
    id("assignment.android.application")
}

android {
    setNamespace("app")

    defaultConfig {
        applicationId = "co.assignment.booklistproject"
        versionCode = 1
        versionName = "1.0"
        compileSdk = 34
        minSdk = 28
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


dependencies {
    implementation(projects.feature.main)
}