import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version ("1.2.0")
}

version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {/*
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")*/

            dependencies {
                implementation(project(":playermodels"))
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
    }
}

// a temporary workaround for a bug in jsRun invocation - see https://youtrack.jetbrains.com/issue/KT-48273
/*
afterEvaluate {
    rootProject.extensions.configure<NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.0.0"
        versions.webpackCli.version = "4.9.0"
    }
}*/
