import BuildConfig.SystemManager
import org.gradle.api.Project
import java.io.File

object BuildConfig {
    object AppCompose {
        const val PACKAGE_NAME = "HDRSwitcher"
        const val PACKAGE_VERSION = "1.0.0"
    }

    object SystemManager {
        const val FILE_NAME = "system_manager.exe"
    }
}

private val Project.rootBuildDir: File get() = rootProject.layout.buildDirectory.asFile.get()
val Project.buildOutput get() = File("${rootBuildDir}/output")

private val Project.appComposeOutputDir get() = File("${buildOutput}/hdrSwitcher")
private val Project.systemManagerOutputDir get() = File("${buildOutput}/systemManager")

val Project.appComposeResourceBinDir
    get() = File("${rootDir}/composeApp/src/jvmMain/resources/bin")

val Project.appComposeReleaseDir
    get() = File("$appComposeOutputDir/release")

val Project.systemManagerDebugFile
    get() = File("$systemManagerOutputDir/debug", SystemManager.FILE_NAME)

val Project.systemManagerReleaseFile
    get() = File("$systemManagerOutputDir/release", SystemManager.FILE_NAME)
