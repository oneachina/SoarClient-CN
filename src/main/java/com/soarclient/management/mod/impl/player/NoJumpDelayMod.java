package com.soarclient.management.mod.impl.player;

import com.soarclient.management.mod.Mod;
import com.soarclient.management.mod.ModCategory;
import com.soarclient.skia.font.Icon;
import com.soarclient.skid.events.features.UpdateEvent;
import com.soarclient.utils.IMinecraft;

public class NoJumpDelayMod extends Mod {
	private int ticks = 0;
	private static NoJumpDelayMod instance;

	public NoJumpDelayMod() {
		super("mod.nojumpdelay.name", "mod.nojumpdelay.description", Icon.KEYBOARD_DOUBLE_ARROW_UP, ModCategory.PLAYER);

		instance = this;
	}

	public static NoJumpDelayMod getInstance() {
		return instance;
	}
}
