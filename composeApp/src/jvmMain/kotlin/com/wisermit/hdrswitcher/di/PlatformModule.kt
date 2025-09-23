package com.wisermit.hdrswitcher.di

import com.wisermit.hdrswitcher.system.MacOsSystemInfo
import com.wisermit.hdrswitcher.system.Platform
import com.wisermit.hdrswitcher.system.SystemInfo
import com.wisermit.hdrswitcher.system.WindowsSystemInfo
import com.wisermit.hdrswitcher.system.manager.MacOsSystemManager
import com.wisermit.hdrswitcher.system.manager.SystemManager
import com.wisermit.hdrswitcher.system.manager.WindowsSystemManager
import org.koin.dsl.module

val platformModule = when (Platform.Current) {
    Platform.Windows -> windowsPlatformModule
    Platform.MacOS -> macOsPlatformModule
}

private val windowsPlatformModule
    get() = module {
        single<SystemInfo> { WindowsSystemInfo() }
        single<SystemManager> { WindowsSystemManager() }
    }

private val macOsPlatformModule
    get() = module {
        single<SystemInfo> { MacOsSystemInfo() }
        single<SystemManager> { MacOsSystemManager() }
    }