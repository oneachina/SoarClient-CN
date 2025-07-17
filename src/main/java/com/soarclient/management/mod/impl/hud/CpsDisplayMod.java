package com.soarclient.management.mod.impl.hud;

import com.soarclient.event.EventBus;
import com.soarclient.event.client.RenderSkiaEvent;
import com.soarclient.management.mod.api.hud.SimpleHUDMod;
import com.soarclient.skia.font.Icon;
import com.soarclient.utils.CpsCounter;

public class CpsDisplayMod extends SimpleHUDMod {
    private long lastUpdateTime = 0;
    private String displayText = "CPS: 0";

    public CpsDisplayMod() {
        super("mod.cpsdisplay.name", "mod.cpsdisplay.description", Icon.MOUSE);
    }

    public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime > 50) {
            displayText = "CPS: " + CpsCounter.getCps();
            lastUpdateTime = currentTime;
        }
        this.draw();
    };

    @Override
    public String getText() {
        return displayText;
    }

    @Override
    public String getIcon() {
        return Icon.MOUSE;
    }
}
