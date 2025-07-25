package com.soarclient.management.mod.impl.player;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import com.soarclient.event.EventBus;
import com.soarclient.event.client.ClientTickEvent;
import com.soarclient.management.mod.Mod;
import com.soarclient.management.mod.ModCategory;
import com.soarclient.management.mod.settings.impl.BooleanSetting;
import com.soarclient.management.mod.settings.impl.ComboSetting;
import com.soarclient.management.mod.settings.impl.KeybindSetting;
import com.soarclient.skia.font.Icon;

import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;

public class FreelookMod extends Mod {

	private static FreelookMod instance;
	private boolean active;
	private boolean toggled;
	private Perspective prevPerspective;

	private ComboSetting perspectiveSetting = new ComboSetting("setting.perspective", "setting.perspective.description",
			Icon.CAMERASWITCH, this, Arrays.asList("setting.front", "setting.behind"), "setting.behind");
	private BooleanSetting toggleSetting = new BooleanSetting("setting.toggle", "setting.toggle.description",
			Icon.SWITCH, this, false);
	private KeybindSetting keybindSetting = new KeybindSetting("setting.keybind", "setting.keybind.description",
			Icon.KEYBOARD, this, InputUtil.fromKeyCode(GLFW.GLFW_KEY_B, 0));

	public FreelookMod() {
		super("mod.freelook.name", "mod.freelook.description", Icon._360, ModCategory.PLAYER);

		instance = this;
		active = false;
	}

	public final EventBus.EventListener<ClientTickEvent> onClientTick = event -> {

		if (toggleSetting.isEnabled()) {

			if (keybindSetting.isPressed()) {
				toggled = !toggled;

				if (toggled) {
					if (!active) {
						this.start();
					}
				} else {
					if (active) {
						this.stop();
					}
				}
			}
		} else {
			if (keybindSetting.isKeyDown()) {
				if (!active) {
					this.start();
				}
			} else if (active) {
				this.stop();
			}
		}
	};

	private void start() {

		String option = perspectiveSetting.getOption();
		Perspective perspective = option.equals("setting.front") ? Perspective.THIRD_PERSON_FRONT
				: Perspective.THIRD_PERSON_BACK;

		active = true;
		prevPerspective = mc.options.getPerspective();
		mc.options.setPerspective(perspective);
	}

	private void stop() {
		active = false;
		mc.options.setPerspective(prevPerspective);
	}

	public static FreelookMod getInstance() {
		return instance;
	}

	public boolean isActive() {
		return active;
	}
}
