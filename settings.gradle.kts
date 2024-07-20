pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "BookListProject"
include(":app")
include(":core:model")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":feature:main")
include(":feature:search")
include(":feature:favorite")
include(":feature:detail")
include(":core:ui")
include(":core:navigation")
