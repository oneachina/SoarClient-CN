package com.soarclient.skid.events.features;

import com.soarclient.skid.events.EventArgument;
import com.soarclient.skid.events.EventListener;
import net.minecraft.entity.Entity;

import java.util.Objects;

public class AttackEvent extends EventArgument {
    public final Entity entity;

    public AttackEvent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void call(EventListener listener) {
        Objects.requireNonNull(listener).onAttack(this);
    }
}