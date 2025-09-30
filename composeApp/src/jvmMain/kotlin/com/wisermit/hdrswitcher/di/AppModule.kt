package com.wisermit.hdrswitcher.di

import com.wisermit.hdrswitcher.Config
import com.wisermit.hdrswitcher.system.SystemInfo
import com.wisermit.hdrswitcher.system.SystemInfoImpl
import com.wisermit.hdrswitcher.system.SystemManager
import com.wisermit.hdrswitcher.system.SystemManagerImpl
import com.wisermit.hdrswitcher.ui.main.MainViewModel
import org.koin.dsl.module

object AppModule {

    private val systemModule = module {
        single<SystemInfo> { SystemInfoImpl() }
        single<SystemManager> { SystemManagerImpl() }
    }

    private val configModule = module {
        single { Config(get()) }
    }

    private val uiModule = module {
        factory { MainViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    }

    val modules = listOf(
        systemModule,
        configModule,
        dataModule,
        domainModule,
        uiModule
    )
}