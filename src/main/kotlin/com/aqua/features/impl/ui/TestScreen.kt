package com.aqua.features.impl.ui

import com.aqua.event.impl.RenderGameOverlayEvent
import com.aqua.features.api.Feature
import com.aqua.radbus.Listen
import net.minecraft.client.Minecraft
import org.lwjgl.input.Keyboard

class TestScreen: Feature("TestScreen", Keyboard.KEY_H) {

    @Listen
    fun onRender(e: RenderGameOverlayEvent) {
        mc?.fontRendererObj?.drawStringWithShadow("Aqua [v0.0.1]", 2.0F, 2.0F, 0x6bbffa)
        mc?.fontRendererObj?.drawStringWithShadow("FPS" + Minecraft.getDebugFPS(), 2.0F, 3.0F, 0x6bbffa)
    }
}