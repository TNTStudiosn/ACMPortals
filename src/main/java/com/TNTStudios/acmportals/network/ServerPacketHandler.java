package com.TNTStudios.acmportals.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ServerPacketHandler {
    public static final Identifier PORTAL_SPAWN_ID = new Identifier("acmportals", "spawn_portal");
    public static final Identifier PORTAL_REMOVE_ID = new Identifier("acmportals", "remove_portal");

    public static void sendSpawnPortal(ServerPlayerEntity player, String name, Vec3d pos) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(name);
        buf.writeDouble(pos.x);
        buf.writeDouble(pos.y);
        buf.writeDouble(pos.z);
        ServerPlayNetworking.send(player, PORTAL_SPAWN_ID, buf);
    }

    public static void sendRemovePortal(ServerPlayerEntity player, String name) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(name);
        ServerPlayNetworking.send(player, PORTAL_REMOVE_ID, buf);
    }
}
