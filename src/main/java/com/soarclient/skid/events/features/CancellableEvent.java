package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventListener;

public class CancellableEvent implements EventListener {

    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setCancelled() {
        this.cancelled = true;
    }
}
