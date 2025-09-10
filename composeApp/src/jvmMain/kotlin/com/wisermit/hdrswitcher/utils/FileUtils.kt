package com.wisermit.hdrswitcher.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStreamReader

object FileUtils {

    const val APPLICATION_EXTENSION = "exe"

    suspend fun getApplicationDescription(
        file: File
    ): Result<String?> = withContext(Dispatchers.IO) {
        // FIXME: Fix charset (™).
        runCatching {
            val command = listOf(
                "powershell.exe",
                "-Command",
                "(Get-Item '$file').VersionInfo.FileDescription"
            )
            val process = ProcessBuilder(command).start()
            process.waitFor()
            InputStreamReader(process.inputStream)
                .buffered()
                .readLine()
        }
    }

    fun File.isApplication(): Boolean = extension == APPLICATION_EXTENSION
}