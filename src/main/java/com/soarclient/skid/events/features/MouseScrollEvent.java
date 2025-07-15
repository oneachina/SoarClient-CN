package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;

import java.util.Objects;

public class MouseScrollEvent extends EventArgument {
    public final double horizontal;
    public final double vertical;

    public MouseScrollEvent(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onMouseScroll(this);
    }
}