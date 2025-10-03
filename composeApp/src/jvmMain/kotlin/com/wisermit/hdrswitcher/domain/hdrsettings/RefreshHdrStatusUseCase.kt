package com.wisermit.hdrswitcher.domain.hdrsettings

import com.wisermit.hdrswitcher.domain.UseCase
import com.wisermit.hdrswitcher.system.SystemManager

class RefreshHdrStatusUseCase(
    private val systemManager: SystemManager,
) : UseCase<Unit, Unit>() {

    override suspend fun execute(parameters: Unit) =
        systemManager.refreshHdrStatus()
}