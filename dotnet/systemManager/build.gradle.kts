val taskGroup = "system manager"
val projectWorkingDir = File("${projectDir}/src")

tasks.register<Exec>(Tasks.SystemManager.CLEAN) {
    group = taskGroup
    workingDir = projectWorkingDir

    commandLine("dotnet", "clean")

    doLast {
        delete(buildOutput)
    }
}

tasks.register<Exec>(Tasks.SystemManager.PUBLISH_DEBUG_EXE) {
    group = taskGroup
    workingDir = projectWorkingDir

    inputs.files(
        fileTree(projectWorkingDir) {
            exclude("**/bin/**")
            exclude("**/obj/**")
        },
    )

    val outputFile = project.systemManagerDebugFile

    outputs.dir(outputFile.parentFile)

    publishCommandLine(type = "Debug", outputFile = outputFile)
}

tasks.register<Exec>(Tasks.SystemManager.PUBLISH_RELEASE_EXE) {
    group = taskGroup
    workingDir = projectWorkingDir

    inputs.files(
        fileTree(projectWorkingDir) {
            exclude("**/bin/**")
            exclude("**/obj/**")
        },
    )

    val outputFile = project.systemManagerReleaseFile

    outputs.dir(outputFile.parentFile)

    publishCommandLine(type = "Release", outputFile = outputFile)

    doLast {
        println("The EXE is written to $outputFile.")
    }
}

fun AbstractExecTask<*>.publishCommandLine(
    type: String,
    outputFile: File,
    vararg command: Any,
): AbstractExecTask<*> = commandLine(
    "dotnet",
    "publish",
    "-c", "$type,AssemblyName=${outputFile.name.substringBefore(".")}",
    "-r", "win-x64",
    "-p:PublishSingleFile=true",
    "--self-contained", "false",
    "-o", outputFile.parent,
    *command
)