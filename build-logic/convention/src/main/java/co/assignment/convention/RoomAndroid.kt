package co.assignment.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureRoomAndroid() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
    }
    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("room.runtime").get())
        "implementation"(libs.findLibrary("room.ktx").get())
        "ksp"(libs.findLibrary("room.compiler").get())
        "implementation"(libs.findLibrary("gson").get())

    }
}