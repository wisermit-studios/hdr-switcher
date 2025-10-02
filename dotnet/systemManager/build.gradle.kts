val projectSrcDir = File("${projectDir}/src")

interface Injected {
    @get:Inject
    val fs: FileSystemOperations
}

tasks.register<Exec>(Tasks.SystemManager.CLEAN) {
    group = Tasks.SystemManager.GROUP
    workingDir = projectSrcDir

    commandLine("dotnet", "clean")

    val injected = project.objects.newInstance<Injected>()
    val outputDir = systemManagerOutputDir

    doLast {
        injected.fs.delete { delete(outputDir) }
    }
}

listOf(
    "Debug" to project.systemManagerOutputDebugFile,
    "Release" to project.systemManagerOutputReleaseFile,
).forEach { (publishType, outputFile) ->

    tasks.register<Exec>("publish${publishType}Exe") {
        group = Tasks.SystemManager.GROUP
        workingDir = projectSrcDir

        inputs.files(
            fileTree(projectSrcDir) {
                exclude("**/bin/**")
                exclude("**/obj/**")
            },
        )

        outputs.files(fileTree(outputFile.parent))

        commandLine(
            "dotnet",
            "publish",
            "-c", "$publishType,AssemblyName=${outputFile.name.substringBefore(".")}",
            "-r", "win-x64",
            "-p:PublishSingleFile=true",
            "--self-contained", "false",
            "-o", outputFile.parent,
        )

        val startTasks = startTasks

        doLast {
            if (startTasks.contains(name)) {
                logger.lifecycle("The EXE is written to $outputFile.")
            }
        }
    }
}