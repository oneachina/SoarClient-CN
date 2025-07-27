package com.soarclient.utils;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Shader;
import io.github.humbleui.types.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClickEffectRenderer {

    private final List<RippleEffect> ripples = new ArrayList<>();
    private int windowWidth = 1000;
    private int windowHeight = 800;

    public void render(Canvas canvas, Color baseColor) {
        Iterator<RippleEffect> iterator = ripples.iterator();
        while (iterator.hasNext()) {
            RippleEffect ripple = iterator.next();
            if (ripple.isAlive()) {
                ripple.update();
                renderRipple(canvas, ripple, baseColor);
            } else {
                iterator.remove();
            }
        }
    }

    private void renderRipple(Canvas canvas, RippleEffect ripple, Color baseColor) {
        float progress = ripple.getProgress();
        float radius = ripple.getRadius();

        int alpha = (int) (baseColor.getAlpha() * (1 - progress * 0.7f));
        Color color = new Color(
            baseColor.getRed(),
            baseColor.getGreen(),
            baseColor.getBlue(),
            Math.max(0, Math.min(255, alpha))
        );

        // 创建径向渐变
        int centerColor = io.github.humbleui.skija.Color.makeARGB(alpha,
            color.getRed(), color.getGreen(), color.getBlue());
        int edgeColor = io.github.humbleui.skija.Color.makeARGB(0,
            color.getRed(), color.getGreen(), color.getBlue());

        Point center = new Point(ripple.getX(), ripple.getY());
        Shader shader = Shader.makeRadialGradient(
            center,
            radius,
            new int[]{centerColor, edgeColor},
            null
        );

        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);

        canvas.drawCircle(ripple.getX(), ripple.getY(), radius, paint);
    }
}
