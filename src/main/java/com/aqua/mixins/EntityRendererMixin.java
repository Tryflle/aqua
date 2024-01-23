package com.aqua.mixins;

import com.aqua.Aqua;
import com.aqua.event.impl.RenderHandEvent;
import com.aqua.event.impl.RenderWorldEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "renderHand", at = @At("HEAD"))
    public void onRenderHand(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new RenderHandEvent());
    }

    @Inject(method = "renderWorldPass", at = @At("HEAD"))
    public void onRenderWorldPre(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new RenderWorldEvent.Pre());
    }

    @Inject(method = "renderWorldPass", at = @At("RETURN"))
    public void onRenderWorldPost(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new RenderWorldEvent.Post());
    }
}
