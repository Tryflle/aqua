package com.aqua.mixins;

import com.aqua.Aqua;
import com.aqua.event.impl.*;
import com.aqua.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public MovingObjectPosition objectMouseOver;
    @Shadow
    public EntityPlayerSP thePlayer;
    @Shadow
    private int leftClickCounter;

    @Inject(method = "run", at = @At(value = "HEAD"))
    public void onInitPre(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new InitEvent.Pre());
    }

    @Inject(method = "run", at = @At(value = "RETURN"))
    public void onInitPost(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new InitEvent.Post());
    }

    @Inject(method = "clickMouse", at = @At(value = "TAIL"))
    public void onMouseClick(CallbackInfo ci) {
        if (leftClickCounter > 0) {
            if (objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && !objectMouseOver.entityHit.hitByEntity(thePlayer) && objectMouseOver.entityHit instanceof EntityLivingBase) {
                if (thePlayer.fallDistance > 0.0F && !thePlayer.onGround && !thePlayer.isOnLadder() && !thePlayer.isInWater() && !thePlayer.isPotionActive(Potion.blindness) && thePlayer.ridingEntity == null) {
                    thePlayer.onCriticalHit(objectMouseOver.entityHit);
                }
                if (EnchantmentHelper.getModifierForCreature(thePlayer.getHeldItem(), ((EntityLivingBase) objectMouseOver.entityHit).getCreatureAttribute()) > 0.0F) {
                    thePlayer.onEnchantmentCritical(objectMouseOver.entityHit);
                }
            }
            PlayerUtils.swingArm();
        }
    }

    @Inject(method = "clickMouse", at = @At(value = "HEAD"), cancellable = true)
    public void onClickMouse(CallbackInfo ci) {
        final ClickEvent e = new ClickEvent(MouseButton.LEFT);
        Aqua.Companion.getEventBus().publish(e);
        if (e.isCancelled()) ci.cancel();
    }

    @Inject(method = "rightClickMouse", at = @At(value = "HEAD"), cancellable = true)
    public void onRightClickMouse(CallbackInfo ci) {
        final ClickEvent e = new ClickEvent(MouseButton.RIGHT);
        Aqua.Companion.getEventBus().publish(e);
        if (e.isCancelled()) ci.cancel();
    }

    @Inject(method = "runTick", at = @At(value = "HEAD"))
    public void onTick(CallbackInfo ci) {
        Aqua.Companion.getEventBus().publish(new TickEvent());
    }

    @Inject(method = "dispatchKeypresses", at = @At(value = "HEAD"), cancellable = true)
    public void onKeyPress(CallbackInfo ci) {
        final KeyboardEvent e = new KeyboardEvent(Keyboard.getEventKey());
        Aqua.Companion.getEventBus().publish(e);
        if (e.isCancelled()) ci.cancel();
    }
}