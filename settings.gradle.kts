rootProject.name = "KmpPlayer"

pluginManagement {
    plugins {
        kotlin("multiplatform") version "1.5.31"
    }
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
        google()
    }
}
include(":app")
include(":playermodels")
