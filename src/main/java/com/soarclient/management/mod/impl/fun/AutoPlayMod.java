package com.soarclient.management.mod.impl.fun;

import com.soarclient.event.EventBus;
import com.soarclient.event.server.impl.ReceiveChatEvent;
import com.soarclient.management.mod.Mod;
import com.soarclient.management.mod.ModCategory;
import com.soarclient.management.mod.settings.impl.ComboSetting;
import com.soarclient.skia.font.Icon;

import java.util.List;

public class AutoPlayMod extends Mod {
    private ComboSetting modeSetting;
    private static final String HEYPIXEL_END_MESSAGE = "可以用 /hub 退出观察者模式并返回大厅";

    private static final List<String> MODES = List.of(
        "setting.autoplay.mode.heypixel"
    );

    public AutoPlayMod() {
        super("mod.autoplay.name", "mod.autoplay.desc", Icon.PLAY_ARROW, ModCategory.FUN);

        modeSetting = new ComboSetting(
            "setting.autoplay.mode",
            "setting.autoplay.mode.description",
            Icon.SELECT,
            this,
            MODES,
            "setting.autoplay.mode.heypixel"
        );
    }

    public final EventBus.EventListener<ReceiveChatEvent> onReceiveChat = event -> {
        if (mc.player == null) {
            return;
        }

        String mode = modeSetting.getOption();
        if (mode.equals("setting.autoplay.mode.heypixel")) {
            if (event.getMessage().equals(HEYPIXEL_END_MESSAGE)) {
                if (!mc.player.isSpectator() && !mc.player.getAbilities().flying) {
                    mc.player.networkHandler.sendChatCommand("again");
                }
            }
        }
    };
}
