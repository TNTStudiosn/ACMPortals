package com.TNTStudios.acmportals.portal;

import com.TNTStudios.acmportals.network.ServerPacketHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class PortalInstance {
    private final String name;
    private final Vec3d position;
    private final ServerPlayerEntity player;

    public PortalInstance(String name, Vec3d position, ServerPlayerEntity player) {
        this.name = name;
        this.position = position;
        this.player = player;
    }

    public void spawn() {
        ServerPacketHandler.sendSpawnPortal(player, name, position);
    }

    public void remove() {
        ServerPacketHandler.sendRemovePortal(player, name);
    }

    public Vec3d getPosition() {
        return position;
    }
}
