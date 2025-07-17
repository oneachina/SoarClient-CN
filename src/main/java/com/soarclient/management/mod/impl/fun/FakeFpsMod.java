package com.soarclient.management.mod.impl.fun;

import com.soarclient.event.EventBus;
import com.soarclient.event.client.RenderSkiaEvent;
import com.soarclient.management.mod.api.hud.SimpleHUDMod;
import com.soarclient.management.mod.settings.impl.NumberSetting;
import com.soarclient.skia.font.Icon;

public class FakeFpsMod extends SimpleHUDMod {
    private NumberSetting fakefps = new NumberSetting("FakeFps", "set fake fps", Icon.SELECT, this, 120, 0, Integer.MAX_VALUE);

    public FakeFpsMod() {
        super("FakeFpsMod", "FakeFpsMod", Icon._60FPS);

    }

    public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
        this.draw();
    };

    @Override
    public String getText() {
        int fps = (int) fakefps.getValue() + mc.getCurrentFps();
        return fps + " FPS";
    }

    @Override
    public String getIcon() {
        return Icon.MONITOR;
    }
}
