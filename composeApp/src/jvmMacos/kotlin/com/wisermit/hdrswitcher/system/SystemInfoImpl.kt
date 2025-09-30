package com.wisermit.hdrswitcher.system

import java.io.File

class SystemInfoImpl : SystemInfo() {
    override val platform: Platform = Platform.MacOS

    override val systemDrive: File = File("/")

    override val appSettingsDir: File
        get() = File(userHomeDir, "Library/Application Support")

    override val applicationExtension = "app"
}