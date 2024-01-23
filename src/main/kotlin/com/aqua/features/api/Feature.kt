package com.aqua.features.api

import com.aqua.Aqua
import com.aqua.utils.player.PlayerUtils
import net.minecraft.client.Minecraft

open class Feature(var name:String, var bind: Int, val mc: Minecraft? = Minecraft.getMinecraft()) {
    var enabled: Boolean = false
    open fun onEnable() {}
    open fun onDisable() {}

    fun toggle() {
        enabled = !enabled
        if (enabled) {
            PlayerUtils.addChatMessage("$name enabled", true)
            Aqua.eventBus.unsubscribe(this)
            onEnable()
        } else {
            PlayerUtils.addChatMessage("$name disabled", true)
            Aqua.eventBus.unsubscribe(this)
            onDisable()
        }
    }
}