/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.soarclient.libraries.material3.scheme;

import com.soarclient.libraries.material3.dynamiccolor.DynamicScheme;
import com.soarclient.libraries.material3.dynamiccolor.Variant;
import com.soarclient.libraries.material3.hct.Hct;
import com.soarclient.libraries.material3.palettes.TonalPalette;

/**
 * A theme that's slightly more chromatic than monochrome, which is purely black
 * / white / gray.
 */
public class SchemeNeutral extends DynamicScheme {
	public SchemeNeutral(Hct sourceColorHct, boolean isDark, double contrastLevel) {
		super(sourceColorHct, Variant.NEUTRAL, isDark, contrastLevel,
				TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), 12.0),
				TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), 8.0),
				TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), 16.0),
				TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), 2.0),
				TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), 2.0));
	}
}
