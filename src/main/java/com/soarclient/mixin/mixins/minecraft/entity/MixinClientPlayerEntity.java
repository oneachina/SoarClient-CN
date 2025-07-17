package com.soarclient.mixin.mixins.minecraft.entity;

import com.soarclient.utils.CpsCounter;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity {

    @Inject(method = "swingHand", at = @At("HEAD"))
    private void onSwingHand(CallbackInfo ci) {
        CpsCounter.recordClick();
    }
}
