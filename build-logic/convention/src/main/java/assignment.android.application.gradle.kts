import co.assignment.convention.configureHiltAndroid
import co.assignment.convention.configureKotlinAndroid
import co.assignment.convention.configureRoomAndroid
import co.assignment.convention.libs
import gradle.kotlin.dsl.accessors._4b055a01bae563bd2c86a468691a3401.androidTestImplementation
import gradle.kotlin.dsl.accessors._4b055a01bae563bd2c86a468691a3401.implementation


plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureRoomAndroid()

dependencies {
    val libs = project.extensions.libs
    implementation(libs.findLibrary("junit4").get())
    androidTestImplementation(libs.findLibrary("androidx.test.ext").get())
}