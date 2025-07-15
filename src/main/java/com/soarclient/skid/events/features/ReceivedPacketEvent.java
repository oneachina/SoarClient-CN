package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.packet.Packet;

import java.util.Objects;
@Getter
@Setter
public class ReceivedPacketEvent extends EventArgument {
    public final Packet<?> packet;

    public ReceivedPacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onReceivedPacket(this);
    }
}