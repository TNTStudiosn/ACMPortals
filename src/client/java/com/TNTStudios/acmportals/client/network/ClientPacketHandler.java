package com.TNTStudios.acmportals.client.network;

import com.TNTStudios.acmportals.client.PortalRenderHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.PacketByteBuf;

public class ClientPacketHandler {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(
                new Identifier("acmportals", "spawn_portal"),
                (client, handler, buf, responseSender) -> {
                    String name = buf.readString();
                    Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());

                    client.execute(() -> PortalRenderHandler.spawnTemporaryPortal(name, pos));
                }
        );

        ClientPlayNetworking.registerGlobalReceiver(
                new Identifier("acmportals", "remove_portal"),
                (client, handler, buf, responseSender) -> {
                    String name = buf.readString();
                    client.execute(() -> PortalRenderHandler.removePortal(name));
                }
        );

    }
}
