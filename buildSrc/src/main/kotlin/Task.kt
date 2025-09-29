object Tasks {

    const val PROCESS_SYSTEM_MANAGER_DEBUG =
        "processSystemManagerDebug"

    const val PROCESS_SYSTEM_MANAGER_RELEASE =
        "processSystemManagerRelease"

    object SystemManager {
        const val PATH = ":dotnet:systemManager"

        const val PUBLISH_DEBUG_EXE = "publishDebugExe"
        const val PUBLISH_RELEASE_EXE = "publishReleaseExe"
        const val CLEAN = "clean"
    }
}