package com.wisermit.hdrswitcher.system

import java.awt.Robot

object InputSimulator {

    fun sendKeyEvent(vararg keys: Int) {
        Robot().apply {
            keys.forEach(::keyPress)
            keys.forEach(::keyRelease)
        }
    }
}