package com.soarclient.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	public static float roundToPlace(double value, int places) {

		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	public static int clamp(int number, int min, int max) {
		return number < min ? min : Math.min(number, max);
	}

	public static float clamp(float number, float min, float max) {
		return number < min ? min : Math.min(number, max);
	}

	public static double clamp(double number, double min, double max) {
		return number < min ? min : Math.min(number, max);
	}

    public static Double interpolate(double oldValue, double newValue, double interpolationValue){
        return (oldValue + (newValue - oldValue) * interpolationValue);
    }

    public static float interpolateFloat(float oldValue, float newValue, double interpolationValue){
        return interpolate(oldValue, newValue, (float) interpolationValue).floatValue();
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue){
        return interpolate(oldValue, newValue, (float) interpolationValue).intValue();
    }
}
