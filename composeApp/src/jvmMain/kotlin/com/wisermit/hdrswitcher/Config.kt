package com.wisermit.hdrswitcher

import com.wisermit.hdrswitcher.system.SystemInfo
import java.io.File

const val APP_SETTINGS_FOLDER_NAME = "HDR Switcher"
const val APPLICATIONS_FILE_NAME = "applications.json"

class Config(val systemInfo: SystemInfo) {

    val userDataDir: File
        get() = File(systemInfo.appSettingsDir, APP_SETTINGS_FOLDER_NAME)

    val applicationsFile: File
        get() = File(userDataDir, APPLICATIONS_FILE_NAME)
}
