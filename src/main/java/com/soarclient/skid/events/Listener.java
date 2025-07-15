package com.soarclient.skid.events;

import java.io.IOException;

@FunctionalInterface
public interface Listener<Event> {
    void call(Event event) throws IOException;
}
