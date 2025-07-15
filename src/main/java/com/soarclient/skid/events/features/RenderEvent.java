package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Objects;

public class RenderEvent extends EventArgument {
    public final MatrixStack matrix;
    public final float partialTicks;

    public RenderEvent(MatrixStack matrix, Float partialTicks) {
        this.matrix = matrix;
        this.partialTicks = partialTicks;
    }

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onRender(this);
    }
}