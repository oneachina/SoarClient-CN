package com.soarclient.management.mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.soarclient.Soar;
import com.soarclient.management.mod.api.hud.*;
import com.soarclient.management.mod.api.hud.design.*;
import com.soarclient.management.mod.api.hud.design.impl.*;
import com.soarclient.management.mod.impl.fun.*;
import com.soarclient.management.mod.impl.hud.*;
import com.soarclient.management.mod.impl.misc.*;
import com.soarclient.management.mod.impl.player.*;
import com.soarclient.management.mod.impl.render.*;
import com.soarclient.management.mod.impl.settings.*;
import com.soarclient.management.mod.settings.Setting;
import com.soarclient.management.mod.settings.impl.KeybindSetting;

public class ModManager {
	private List<Mod> mods = new ArrayList<>();
	private List<Setting> settings = new ArrayList<>();
	private List<HUDDesign> designs = new ArrayList<>();

	private HUDDesign currentDesign;

	public void init() {
		initMods();
		initDesigns();
	}

	private void initMods() {

		// HUD
		mods.add(new BedwarsStatsOverlayMod());
		mods.add(new BossBarMod());
		mods.add(new ClockMod());
		mods.add(new ComboCounterMod());
		mods.add(new CoordsMod());
		mods.add(new DayCounterMod());
		mods.add(new FPSDisplayMod());
		mods.add(new GameModeDisplayMod());
		mods.add(new HealthDisplayMod());
		mods.add(new JumpResetIndicatorMod());
		mods.add(new KeystrokesMod());
		mods.add(new MemoryUsageMod());
		mods.add(new MouseStrokesMod());
		mods.add(new MusicInfoMod());
		mods.add(new NameDisplayMod());
		mods.add(new PingDisplayMod());
		mods.add(new PitchDisplayMod());
		mods.add(new PlayerCounterMod());
		mods.add(new PlayTimeDisplayMod());
		mods.add(new ProtocolVersionMod());
		mods.add(new ReachDisplayMod());
		mods.add(new ServerIPDisplayMod());
		mods.add(new SpeedometerMod());
		mods.add(new StopwatchMod());
		mods.add(new WebBrowserMod());
		mods.add(new WeatherDisplayMod());
		mods.add(new YawDisplayMod());
        mods.add(new WatermarkMod());
        mods.add(new CpsDisplayMod());

		// Player
		mods.add(new AutoGGMod());
		mods.add(new ForceMainHandMod());
		mods.add(new FreelookMod());
		mods.add(new HitDelayFixMod());
		mods.add(new NoJumpDelayMod());
		mods.add(new OldAnimationsMod());
		mods.add(new SnapTapMod());
		mods.add(new TaplookMod());
		mods.add(new ZoomMod());
        mods.add(new AutoTextMod());
        mods.add(new AutoPlayMod());

		// Render
		mods.add(new BloodParticleMod());
		mods.add(new CustomHandMod());
		mods.add(new FullbrightMod());
		mods.add(new MusicWaveformMod());
		mods.add(new OverlayEditorMod());
		mods.add(new ParticlesMod());
		mods.add(new ProjectileTrailMod());
        mods.add(new ClickEffectMod());

		// Misc
		mods.add(new DiscordRPCMod());
		mods.add(new HypixelMod());
		mods.add(new TimeChangerMod());
		mods.add(new WeatherChangerMod());
		
		// Settings
		mods.add(new HUDModSettings());
		mods.add(new ModMenuSettings());
		mods.add(new SystemSettings());

        //Fun
        mods.add(new FakeFpsMod());

		sortMods();
        ClickEffectMod clickEffectMod = new ClickEffectMod();
        clickEffectMod.setEnabled(true);
	}

	private void initDesigns() {
		designs.add(new ClassicDesign());
		designs.add(new ClearDesign());
		designs.add(new MaterialYouDesign());
		designs.add(new SimpleDesign());
		setCurrentDesign("design.simple");
	}

	public List<Mod> getMods() {
		return mods;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public List<HUDMod> getHUDMods() {
		return mods.stream().filter(m -> m instanceof HUDMod).map(m -> (HUDMod) m).collect(Collectors.toList());
	}

	public List<KeybindSetting> getKeybindSettings() {
		return settings.stream().filter(s -> s instanceof KeybindSetting).map(s -> (KeybindSetting) s)
				.collect(Collectors.toList());
	}

	public List<Setting> getSettingsByMod(Mod m) {
		return settings.stream().filter(s -> s.getParent().equals(m)).collect(Collectors.toList());
	}

	public void addSetting(Setting setting) {
		settings.add(setting);
	}

	public HUDDesign getCurrentDesign() {
		return currentDesign;
	}

	public void setCurrentDesign(String name) {
		this.currentDesign = getDesignByName(name);
	}

	public HUDDesign getDesignByName(String name) {
		return designs.stream().filter(design -> design.getName().equals(name)).findFirst()
				.orElseGet(() -> getDesignByName("design.simple"));
	}

	private void sortMods() {
		mods.sort((mod1, mod2) -> mod1.getName().compareTo(mod2.getName()));
	}
}
