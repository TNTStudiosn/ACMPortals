package com.TNTStudios.acmportals.portal;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class PortalManager {
    private static final HashMap<String, PortalInstance> activePortals = new HashMap<>();

    public static void spawnPortal(ServerCommandSource source, String name) {
        ServerPlayerEntity player = source.getPlayer();
        Vec3d pos = player.getPos().add(player.getRotationVec(1.0f).multiply(3)); // 3 bloques al frente
        PortalInstance portal = new PortalInstance(name, pos);
        portal.spawn();
        activePortals.put(name, portal);
    }

    public static void removePortal(ServerCommandSource source, String name) {
        if (activePortals.containsKey(name)) {
            activePortals.get(name).remove();
            activePortals.remove(name);
        }
    }
}
