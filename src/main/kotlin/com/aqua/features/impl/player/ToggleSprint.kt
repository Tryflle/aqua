package com.aqua.features.impl.player

import com.aqua.event.impl.KeyboardEvent
import com.aqua.features.api.Feature
import com.aqua.radbus.Listen
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard

class ToggleSprint: Feature("ToggleSprint", Keyboard.KEY_L) {

    @Listen
    fun onTick(e: TickEvent.ClientTickEvent) {
        if (shouldSprint) {
            KeyBinding.setKeyBindState(mc?.gameSettings?.keyBindSprint?.keyCode ?: 0, true)
        }
    }

    @Listen
    fun onKeyPress(e: KeyboardEvent) {
        if (Keyboard.getEventKey() == mc?.gameSettings?.keyBindSprint?.keyCode) {
            shouldSprint = !shouldSprint
        }
    }
    companion object {
        private var shouldSprint: Boolean = true
    }
}