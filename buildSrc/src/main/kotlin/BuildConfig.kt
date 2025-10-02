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

val Project.systemManagerOutputDir get() = File("${rootBuildDir}/output/systemManager")

val Project.systemManagerOutputDebugFile
    get() = File("$systemManagerOutputDir/debug", SystemManager.FILE_NAME)

val Project.systemManagerOutputReleaseFile
    get() = File("$systemManagerOutputDir/release", SystemManager.FILE_NAME)

val Project.appComposeWindowsResourceBinDir
    get() = File("${rootDir}/composeApp/src/jvmWindows/resources/bin")