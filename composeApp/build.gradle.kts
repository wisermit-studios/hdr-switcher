import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.desktop.application.tasks.AbstractJPackageTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
//    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

kotlin {
    jvmToolchain(21)
    jvm()

    sourceSets {
        all {
            languageSettings {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("androidx.compose.ui.ExperimentalComposeUiApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(compose.desktop.currentOs)
            implementation(libs.net.java.jna)
            implementation(libs.net.java.jna.platform)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.wisermit.hdrswitcher.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Dmg)
            packageName = BuildConfig.AppCompose.PACKAGE_NAME
            packageVersion = BuildConfig.AppCompose.PACKAGE_VERSION

            windows {
                menu = true
                shortcut = false
            }
        }
    }

    afterEvaluate {
        tasks.named<Copy>("jvmProcessResources") {
            val isReleaseTask = gradle.startParameter.taskNames.any { it.contains("Release") }

            val taskName = when (isReleaseTask) {
                true -> Tasks.PROCESS_SYSTEM_MANAGER_RELEASE
                false -> Tasks.PROCESS_SYSTEM_MANAGER_DEBUG
            }

            from(rootProject.tasks.named(taskName))
        }
        tasks.named<AbstractJPackageTask>("packageReleaseMsi") {
            destinationDir.set(project.appComposeReleaseDir)
        }
        tasks.named<AbstractJPackageTask>("createReleaseDistributable") {
            destinationDir.set(project.appComposeReleaseDir)
        }
    }
}

