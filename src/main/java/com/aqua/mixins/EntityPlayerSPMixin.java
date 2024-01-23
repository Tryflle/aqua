package com.aqua.mixins;


import com.aqua.Aqua;
import com.aqua.event.impl.ChatSendEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void onChatSend(String message, CallbackInfo ci) {
        final ChatSendEvent e = new ChatSendEvent(message);
        Aqua.Companion.getEventBus().publish(e);
        if (e.isCancelled()) ci.cancel();
    }
}
