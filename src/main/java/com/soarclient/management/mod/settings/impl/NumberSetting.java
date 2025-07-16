package com.soarclient.management.mod.settings.impl;

import com.soarclient.Soar;
import com.soarclient.management.mod.Mod;
import com.soarclient.management.mod.settings.Setting;
import com.soarclient.utils.MathUtils;

public class NumberSetting extends Setting {

	private float defaultValue, value, minValue, maxValue;
	private float step;

	public NumberSetting(String name, String description, String icon, Mod parent, float defaultValue, float minValue,
			float maxValue, float step) {
		super(name, description, icon, parent);

		this.value = defaultValue;
		this.defaultValue = defaultValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.step = step;

		Soar.getInstance().getModManager().addSetting(this);
	}

    public NumberSetting(String name, String description, String icon, Mod parent, int defaultIntValue, int minValue,
                         int maxValue) {
        super(name, description, icon, parent);

        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = 1;

        Soar.getInstance().getModManager().addSetting(this);
    }

	@Override
	public void reset() {
		setValue(defaultValue);
	}

	public float getValue() {
		return value;
	}

	public float getMinValue() {
		return minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setValue(float value) {
		value = MathUtils.clamp(value, this.minValue, this.maxValue);
		this.value = value;
	}

	public float getStep() {
		return step;
	}

	public void setStep(float step) {
		this.step = step;
	}

	public float getDefaultValue() {
		return defaultValue;
	}
}
