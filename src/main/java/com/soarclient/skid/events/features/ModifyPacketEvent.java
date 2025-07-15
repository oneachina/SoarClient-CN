package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;
import net.minecraft.network.packet.Packet;

import java.util.Objects;

public class ModifyPacketEvent extends EventArgument {
    public Packet<?> packet;

    public ModifyPacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onModifyPacket(this);
    }
}