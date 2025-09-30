package com.wisermit.hdrswitcher.system

import com.sun.jna.platform.win32.KnownFolders
import com.sun.jna.platform.win32.Shell32Util
import java.io.File

private val OS_NAME: String = System.getProperty("os.name")

enum class Platform {
    Windows, MacOS;

    companion object {
        val Current: Platform by lazy {
            when {
                OS_NAME.startsWith("Win") -> Windows
                OS_NAME.startsWith("Mac") -> MacOS
                else -> throw UnsupportedOperationException("Unsupported OS: $OS_NAME")
            }
        }
    }
}

abstract class SystemInfo {
    abstract val platform: Platform

    @Suppress("unused")
    val osName: String = OS_NAME

    abstract val systemDrive: File

    val userHomeDir: File get() = File(System.getProperty("user.home"))
    abstract val appSettingsDir: File

    abstract val applicationExtension: String
}

internal class WindowsSystemInfo : SystemInfo() {
    override val platform: Platform = Platform.Windows

    override val systemDrive: File = File(System.getenv("SystemDrive"))

    override val appSettingsDir: File
        get() = Shell32Util.getKnownFolderPath(KnownFolders.FOLDERID_Documents)
            ?.let(::File)
            ?: File(userHomeDir, "Documents")

    override val applicationExtension = "exe"
}

/*
 * The app is not functional on macOS. This is for development purposes only.
 */
internal class MacOsSystemInfo : SystemInfo() {
    override val platform: Platform = Platform.MacOS

    override val systemDrive: File = File("/")

    override val appSettingsDir: File
        get() = File(userHomeDir, "Library/Application Support")

    override val applicationExtension = "app"
}