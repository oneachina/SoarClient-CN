package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;

import java.util.Objects;

public class UpdateEvent extends EventArgument {

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onUpdate(this);
    }
}
