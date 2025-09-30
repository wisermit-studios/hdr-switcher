package com.wisermit.hdrswitcher.system

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