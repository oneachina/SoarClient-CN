package com.soarclient.mixin.mixins.minecraft.client;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.soarclient.Soar;
import com.soarclient.management.mod.settings.impl.KeybindSetting;

import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;

@Mixin(Keyboard.class)
public abstract class MixinKeyboard {

    @Inject(
        method = "onKey(JIIII)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/option/KeyBinding;onKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;)V",
            shift = At.Shift.AFTER
        )
    )
    private void onKeyPressed(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            InputUtil.Key inputKey = InputUtil.fromKeyCode(key, scancode);
            for (KeybindSetting setting : Soar.getInstance().getModManager().getKeybindSettings()) {
                if (setting.getKey().equals(inputKey)) {
                    setting.setPressed();
                    setting.setKeyDown(true);
                }
            }
        }
    }

    @Inject(
        method = "onKey(JIIII)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/option/KeyBinding;setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V",
            shift = At.Shift.AFTER
        )
    )
    private void onKeyReleased(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (action == GLFW.GLFW_RELEASE) {
            InputUtil.Key inputKey = InputUtil.fromKeyCode(key, scancode);
            for (KeybindSetting setting : Soar.getInstance().getModManager().getKeybindSettings()) {
                if (setting.getKey().equals(inputKey)) {
                    setting.setKeyDown(false);
                }
            }
        }
    }
}
