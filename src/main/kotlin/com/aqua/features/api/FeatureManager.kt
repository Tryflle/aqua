package com.aqua.features.api

import com.aqua.event.impl.KeyboardEvent
import com.aqua.features.impl.player.ToggleSprint
import com.aqua.features.impl.ui.TestScreen
import com.aqua.radbus.Listen
import org.lwjgl.input.Keyboard

object FeatureManager {

    var features: HashMap<String, Feature> = (
        hashMapOf(
            ToggleSprint().name to ToggleSprint(),
            TestScreen().name to TestScreen()
        )
    )

    @Listen
    fun onKeyPress(e: KeyboardEvent) {
        features.values.find { it.bind == e.keyCode && Keyboard.isKeyDown(it.bind) }?.toggle() ?: return
    }
}