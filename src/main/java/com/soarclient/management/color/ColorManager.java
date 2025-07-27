package com.soarclient.management.color;

import com.soarclient.libraries.material3.hct.Hct;
import com.soarclient.management.color.api.ColorPalette;
import com.soarclient.management.mod.impl.settings.ModMenuSettings;

import java.awt.*;
import java.util.ArrayList;

public class ColorManager {

    private ArrayList<AccentColor> colors = new ArrayList<AccentColor>();
	private ColorPalette palette;
    private AccentColor currentColor;

	public ColorManager() {
		updatePalette();
	}

	public void onTick() {

		ModMenuSettings m = ModMenuSettings.getInstance();
		Hct hct = m.getHctColorSetting().getHct();

		if (palette == null) {
			updatePalette();
		}

		if (palette.isDarkMode() != m.getDarkModeSetting().isEnabled()) {
			updatePalette();
		}

		if (palette.getHct() != hct) {
			updatePalette();
		}
	}

	private void updatePalette() {

		ModMenuSettings m = ModMenuSettings.getInstance();

		palette = new ColorPalette(m.getHctColorSetting().getHct(), m.getDarkModeSetting().isEnabled());
	}

	public ColorPalette getPalette() {
		return palette;
	}

    public AccentColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(AccentColor currentColor) {
        this.currentColor = currentColor;
    }

    private void add(String name, Color color1, Color color2) {
        colors.add(new AccentColor(name, color1, color2));
    }

    public ArrayList<AccentColor> getColors() {
        return colors;
    }
}
