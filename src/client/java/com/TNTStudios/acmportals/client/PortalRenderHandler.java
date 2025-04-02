package com.TNTStudios.acmportals.client;

import com.TNTStudios.acmportals.client.shader.DoctorStrangeShader;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class PortalRenderHandler {
    private static final HashMap<String, Vec3d> activePortals = new HashMap<>();

    public static void spawnTemporaryPortal(String name, Vec3d pos) {
        activePortals.put(name, pos);
    }

    public static void init() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MatrixStack matrices = context.matrixStack();
            double camX = context.camera().getPos().x;
            double camY = context.camera().getPos().y;
            double camZ = context.camera().getPos().z;
            float tickDelta = MinecraftClient.getInstance().getTickDelta();

            activePortals.forEach((name, pos) -> {
                double x = pos.x - camX;
                double y = pos.y - camY;
                double z = pos.z - camZ;

                DoctorStrangeShader.render(matrices, x, y, z, tickDelta, (float)(System.currentTimeMillis() % 10000) / 1000f);
            });
        });
    }

    public static void removePortal(String name) {
        activePortals.remove(name);
    }

}
