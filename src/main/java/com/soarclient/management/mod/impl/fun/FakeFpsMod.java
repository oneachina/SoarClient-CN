package com.soarclient.management.mod.impl.fun;

import com.soarclient.event.EventBus;
import com.soarclient.event.client.RenderSkiaEvent;
import com.soarclient.management.mod.api.hud.SimpleHUDMod;
import com.soarclient.management.mod.settings.impl.ComboSetting;
import com.soarclient.management.mod.settings.impl.NumberSetting;
import com.soarclient.skia.font.Icon;
import net.minecraft.client.MinecraftClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakeFpsMod extends SimpleHUDMod {
    private final ComboSetting modeSetting;
    private final NumberSetting multiplierSetting;
    private final NumberSetting randomDigitsSetting;

    private final Random random = new Random();
    private int currentRandomFps = 0;
    private long lastUpdateTime = 0;

    private static final List<String> MODES = Arrays.asList(
        "setting.fakefps.mode.real",       // 显示真实FPS
        "setting.fakefps.mode.multiplied", // 显示倍数FPS
        "setting.fakefps.mode.random",     // 显示随机FPS
        "setting.fakefps.mode.fixed"       // 显示固定FPS
    );

    public FakeFpsMod() {
        super("mod.fakefps.name", "mod.fakefps.description", Icon.MONITOR);

        modeSetting = new ComboSetting(
            "setting.fakefps.mode",
            "setting.fakefps.mode.description",
            Icon.SELECT,
            this,
            MODES,
            "Real"
        );

        multiplierSetting = new NumberSetting(
            "setting.fakefps.multiplier",
            "setting.fakefps.multiplier.description",
            Icon.ADD,
            this,
            2.0f,
            0.1f,
            10.0f,
            0.1f
        );

        randomDigitsSetting = new NumberSetting(
            "setting.fakefps.randomdigits",
            "setting.fakefps.randomdigits.description",
            Icon.NUMBERS,
            this,
            3,  // 默认3位数
            1,  // 最小1位
            5,   // 最大5位
            1
        );
    }

    public final EventBus.EventListener<RenderSkiaEvent> onRenderSkia = event -> {
        this.draw();
    };

    @Override
    public String getText() {
        int realFps = MinecraftClient.getInstance().getCurrentFps();
        String mode = modeSetting.getOption();

        switch (mode) {
            case "setting.fakefps.mode.multiplied":
                return (int)(realFps * multiplierSetting.getValue()) + " FPS";

            case "setting.fakefps.mode.random":
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastUpdateTime > 1000) {
                    currentRandomFps = generateRandomFps();
                    lastUpdateTime = currentTime;
                }
                return currentRandomFps + " FPS";

            case "setting.fakefps.mode.fixed":
                return (int)multiplierSetting.getValue() + " FPS";

            default:
                return realFps + " FPS";
        }
    }

    private int generateRandomFps() {
        int digits = (int)randomDigitsSetting.getValue();
        int min = (int)Math.pow(10, digits - 1);
        int max = (int)Math.pow(10, digits) - 1;
        return min + random.nextInt(max - min + 1);
    }

    @Override
    public String getIcon() {
        return Icon.MONITOR;
    }

}
