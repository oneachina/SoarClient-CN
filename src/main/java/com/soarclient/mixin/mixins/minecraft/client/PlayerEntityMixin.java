package com.soarclient.mixin.mixins.minecraft.client;

import com.soarclient.Soar;
import com.soarclient.skid.events.features.UpdateEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    private void updateEvent(CallbackInfo ci) {
        if (!((Object) this instanceof ClientPlayerEntity)) return;

        UpdateEvent event = new UpdateEvent();
        Objects.requireNonNull(Soar.eventManager).call(event);
        if (event.isCancelled())
            ci.cancel();
    }

}
