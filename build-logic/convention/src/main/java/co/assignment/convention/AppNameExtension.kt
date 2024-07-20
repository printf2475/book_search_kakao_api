package co.assignment.convention

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "co.assignment.$name"
    }
}