import BuildConfig.SystemManager
import org.gradle.api.Project
import java.io.File

private val Project.rootBuildDir: File get() = rootProject.layout.buildDirectory.asFile.get()
val Project.buildOutput get() = File("${rootBuildDir}/output")

private val Project.appComposeOutputDir get() = File("${buildOutput}/hdrSwitcher")
private val Project.systemManagerOutputDir get() = File("${buildOutput}/systemManager")

val Project.systemManagerOutputDebugFile
    get() = File("$systemManagerOutputDir/debug", SystemManager.FILE_NAME)

val Project.systemManagerOutputReleaseFile
    get() = File("$systemManagerOutputDir/release", SystemManager.FILE_NAME)

val Project.appComposeReleaseDir
    get() = File("$appComposeOutputDir/release")

val Project.appComposeWindowsResourceBinDir
    get() = File("${rootDir}/composeApp/src/jvmWindows/resources/bin")

val Project.buildType: BuildType
    get() {
        val startTask = gradle.startParameter.taskNames.firstOrNull()
        return when {
            startTask == null || startTask == "run" -> localProperties.buildType
            startTask.endsWith("Release") -> BuildType.Release
            else -> BuildType.Debug
        }
    }

val Project.buildTarget: BuildTarget
    get() {
        val startTask = gradle.startParameter.taskNames.firstOrNull()
        return when {
            startTask == null || startTask.startsWith("run") -> localProperties.buildTarget
            startTask.endsWith("Dmg") -> BuildTarget.Macos
            else -> BuildTarget.Windows
        }
    }

enum class BuildType() {
    Debug, Release;

    val value get() = name.lowercase()

    override fun toString() = value
}

enum class BuildTarget {
    Windows, Macos;

    val value: String get() = name.lowercase()

    override fun toString() = value
}