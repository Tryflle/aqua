package com.aqua.utils.player

import com.aqua.mixins.IEntityLivingBaseMixin
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText

private val mc = Minecraft.getMinecraft()

object PlayerUtils {

    fun addChatMessage(message: String, watermark: Boolean) {
        if (watermark) mc?.thePlayer?.addChatMessage(ChatComponentText("§7[§bAqua§7] §r$message"))
        else mc?.thePlayer?.addChatMessage(ChatComponentText(message))
    }

    @JvmStatic
    fun swingArm() {
        if (mc.thePlayer != null && !mc.thePlayer.isSwingInProgress || mc.thePlayer.swingProgressInt >= (mc.thePlayer as IEntityLivingBaseMixin).getArmSwingAnimation() / 2 || mc.thePlayer.swingProgressInt < 0) {
            mc.thePlayer.swingProgressInt = -1
            mc.thePlayer.isSwingInProgress = true
        }
    }
}