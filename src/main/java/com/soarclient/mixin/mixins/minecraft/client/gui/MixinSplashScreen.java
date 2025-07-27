package com.soarclient.mixin.mixins.minecraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
public abstract class MixinSplashScreen {
    @Unique
    private long soar_startTime = -0L;
    @Unique
    private static final Identifier CUSTOM_LOGO = Identifier.of("soar", "logo.png");
    @Unique
    private static final int LOGO_ACTUAL_SIZE = 1080;
    @Unique
    private static final float LOGO_SCALE = 0.15f;

    // 进度条设置
    @Unique
    private static final int PROGRESS_BAR_HEIGHT = 8;
    @Unique
    private static final int PROGRESS_BAR_WIDTH = 300;
    @Unique
    private static final int PROGRESS_BAR_CORNER_RADIUS = 4;
    @Unique
    private static final int PROGRESS_BAR_PADDING = 2;
    @Unique
    private static final int PROGRESS_BAR_Y_OFFSET = 40; // Logo下方偏移量

    @Inject(method = "render", at = @At("TAIL"))
    private void SoarSplashScreen(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {

        long currentTime = Util.getMeasuringTimeMs();
        if (this.soar_startTime == -0L) {
            this.soar_startTime = currentTime;
        }

        long minDisplayTime = 4000L;
        long fadeOutDuration = 500L;
        float alpha = 1.0F;

        long timePassed = currentTime - this.soar_startTime;
        if (timePassed > minDisplayTime) {
            long fadeTime = timePassed - minDisplayTime;
            alpha = 1.0F - (float) fadeTime / fadeOutDuration;
        }

        if (alpha > 0.0F) {
            alpha = Math.min(1.0F, alpha);
            int width = context.getScaledWindowWidth();
            int height = context.getScaledWindowHeight();

            RenderSystem.enableBlend();
            context.fill(0, 0, width, height, (int)(alpha * 255.0F) << 24);

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
            context.getMatrices().push();
            try {
                int scaledSize = (int)(LOGO_ACTUAL_SIZE * LOGO_SCALE);
                int logoX = (width - scaledSize) / 2;
                int logoY = (height - scaledSize) / 2;

                context.getMatrices().translate(logoX + scaledSize / 2f, logoY + scaledSize / 2f, 0);
                context.getMatrices().scale(LOGO_SCALE, LOGO_SCALE, 1.0f);
                context.getMatrices().translate(-LOGO_ACTUAL_SIZE / 2f, -LOGO_ACTUAL_SIZE / 2f, 0);

                context.drawTexture(
                    RenderLayer::getGuiTextured,
                    CUSTOM_LOGO,
                    0, 0, 0, 0,
                    LOGO_ACTUAL_SIZE, LOGO_ACTUAL_SIZE,
                    LOGO_ACTUAL_SIZE, LOGO_ACTUAL_SIZE
                );
            } finally {
                context.getMatrices().pop();

                // ============= 添加进度条 =============
                int progressBarX = (width - PROGRESS_BAR_WIDTH) / 2;
                int progressBarY = (height + (int)(LOGO_ACTUAL_SIZE * LOGO_SCALE) / 2) + PROGRESS_BAR_Y_OFFSET;

                // 计算加载进度 (0.0 - 1.0)
                float progress = Math.min(1.0f, (float) timePassed / minDisplayTime);

                // 绘制进度条背景
                int backgroundColor = (int)(alpha * 100) << 24; // 半透明黑色背景
                context.fill(
                    progressBarX - PROGRESS_BAR_PADDING,
                    progressBarY - PROGRESS_BAR_PADDING,
                    progressBarX + PROGRESS_BAR_WIDTH + PROGRESS_BAR_PADDING,
                    progressBarY + PROGRESS_BAR_HEIGHT + PROGRESS_BAR_PADDING,
                    backgroundColor
                );

                // 绘制进度条轨道
                int trackColor = (int)(alpha * 50) << 24; // 更暗的背景
                context.fill(
                    progressBarX,
                    progressBarY,
                    progressBarX + PROGRESS_BAR_WIDTH,
                    progressBarY + PROGRESS_BAR_HEIGHT,
                    trackColor
                );

                // 绘制进度条前景
                int progressWidth = (int)(PROGRESS_BAR_WIDTH * progress);
                int progressColor = 0xFFFFFFFF; // 白色
                context.fill(
                    progressBarX,
                    progressBarY,
                    progressBarX + progressWidth,
                    progressBarY + PROGRESS_BAR_HEIGHT,
                    progressColor
                );

                // 绘制进度条圆角效果
                if (progressWidth > PROGRESS_BAR_CORNER_RADIUS) {
                    // 左上角圆角
                    context.fill(
                        progressBarX,
                        progressBarY,
                        progressBarX + PROGRESS_BAR_CORNER_RADIUS,
                        progressBarY + PROGRESS_BAR_CORNER_RADIUS,
                        trackColor
                    );

                    // 左下角圆角
                    context.fill(
                        progressBarX,
                        progressBarY + PROGRESS_BAR_HEIGHT - PROGRESS_BAR_CORNER_RADIUS,
                        progressBarX + PROGRESS_BAR_CORNER_RADIUS,
                        progressBarY + PROGRESS_BAR_HEIGHT,
                        trackColor
                    );

                    // 右上角圆角（只在进度条末端绘制）
                    if (progressWidth > PROGRESS_BAR_WIDTH - PROGRESS_BAR_CORNER_RADIUS) {
                        int endX = progressBarX + progressWidth;
                        context.fill(
                            endX - PROGRESS_BAR_CORNER_RADIUS,
                            progressBarY,
                            endX,
                            progressBarY + PROGRESS_BAR_CORNER_RADIUS,
                            trackColor
                        );

                        context.fill(
                            endX - PROGRESS_BAR_CORNER_RADIUS,
                            progressBarY + PROGRESS_BAR_HEIGHT - PROGRESS_BAR_CORNER_RADIUS,
                            endX,
                            progressBarY + PROGRESS_BAR_HEIGHT,
                            trackColor
                        );
                    }
                }

                RenderSystem.disableBlend();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // 重置颜色
            }
        }
    }
}
