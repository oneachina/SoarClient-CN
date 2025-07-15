package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class KeyboardEvent extends EventArgument {

    public final int key, action;

    public KeyboardEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }

    @Override
    public void call(@Nullable EventListener listener) {
        Objects.requireNonNull(listener).onKey(this);
    }
}
