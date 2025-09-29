plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
}

tasks.register<Copy>(Tasks.PROCESS_SYSTEM_MANAGER_DEBUG) {
    description = "Copy the SystemManger Debug EXE to ComposeApp Resource folder."

    dependsOn(with(Tasks.SystemManager) { "$PATH:$PUBLISH_DEBUG_EXE" })

    from(systemManagerDebugFile)
    into(appComposeResourceBinDir)
}

tasks.register<Copy>(Tasks.PROCESS_SYSTEM_MANAGER_RELEASE) {
    description = "Copy the SystemManger Debug EXE to ComposeApp Resource folder."

    dependsOn(with(Tasks.SystemManager) { "$PATH:$PUBLISH_RELEASE_EXE" })

    from(systemManagerReleaseFile)
    into(appComposeResourceBinDir)
}