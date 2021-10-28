import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
    js(IR) {
        useCommonJs()
        browser()
    }
    sourceSets {
        val coroutinesVersion = "1.5.1-native-mt"
        val commonMain by getting{
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
           /* dependencies {
                implementation(kotlin("stdlib-js", "1.5.31"))
            }*/
            /*resources.srcDir("src/main/resources")
            kotlin.srcDir("src/main/kotlin")*/
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}