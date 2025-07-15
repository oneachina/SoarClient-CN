package com.soarclient.skid.events;

public abstract class EventArgument {
    private boolean cancelled = false;

    public final boolean isCancelled() {
        return cancelled;
    }

    public final void cancel() {
        this.cancelled = true;
    }

    public abstract void call(EventListener listener);
}
