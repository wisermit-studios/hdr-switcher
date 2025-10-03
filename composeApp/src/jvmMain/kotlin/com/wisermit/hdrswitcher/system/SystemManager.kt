package com.wisermit.hdrswitcher.system

import kotlinx.coroutines.flow.Flow
import java.io.File

interface SystemManager {

    fun getHdrStatus(): Flow<Boolean?>

    fun refreshHdrStatus()

    fun toggleHdr()

    suspend fun getFileDescription(file: File): String?
}