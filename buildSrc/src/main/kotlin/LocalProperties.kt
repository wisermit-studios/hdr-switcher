import org.gradle.api.Project
import java.util.Properties

class LocalProperties(rootProject: Project) {

    private val file = rootProject.file("local.properties")
    private val properties = Properties().apply {
        file.inputStream().use { load(it) }
    }

    val buildType: BuildType
        get() {
            val buildType = properties[BUILD_TYPE]
                ?: return BuildType.Debug
                    .also { addProperty(BUILD_TYPE, it.value) }

            return BuildType.entries
                .find { it.value == buildType }
                ?: throw Exception(
                    "Invalid $BUILD_TYPE '$buildType'. Expected values: ${BuildType.entries}."
                )
        }

    val buildTarget: BuildTarget
        get() {
            val buildTarget = properties[BUILD_TARGET]
                ?: when (System.getProperty("os.name").startsWith("Mac")) {
                    true -> BuildTarget.Macos
                    else -> BuildTarget.Windows
                }.also {
                    addProperty(BUILD_TARGET, it.value)
                }

            return BuildTarget.entries
                .find { it.value == buildTarget }
                ?: throw Exception(
                    "Invalid $BUILD_TARGET '$buildTarget'. Expected values: ${BuildTarget.entries}."
                )
        }

    private fun addProperty(property: String, value: String) {
        properties[property] = value
        file.appendText("\n$property=$value")
    }

    private companion object {
        const val BUILD_TYPE = "buildType"
        const val BUILD_TARGET = "buildTarget"
    }
}

val Project.localProperties get() = LocalProperties(rootProject)

