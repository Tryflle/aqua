package com.aqua.mixins;

import com.aqua.Aqua;
import com.aqua.event.impl.RenderGameOverlayEvent;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiInGameMixin {

    @Inject(method = "renderGameOverlay", at = @At(value = "HEAD"))
    public void onRenderGameOverlay(float partialTicks, CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new RenderGameOverlayEvent.Pre(partialTicks));
    }

    @Inject(method = "renderGameOverlay", at = @At(value = "RETURN"))
    public void onRenderGameOverlayPost(float partialTicks, CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new RenderGameOverlayEvent.Post(partialTicks));
    }
}
