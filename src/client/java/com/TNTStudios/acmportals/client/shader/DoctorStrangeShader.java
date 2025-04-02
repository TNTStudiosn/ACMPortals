package com.TNTStudios.acmportals.client.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import net.minecraft.client.gl.ShaderProgram;

public class DoctorStrangeShader {
    public static ShaderProgram shader;

    public static void init() {
        try {
            String shaderId = "doctorstrange";
            System.out.println("Attempting to load shader with ID: " + shaderId);
            Identifier id = new Identifier(shaderId);
            System.out.println("Namespace: " + id.getNamespace());
            System.out.println("Path: " + id.getPath());
            shader = new ShaderProgram(
                    MinecraftClient.getInstance().getResourceManager(),
                    shaderId,
                    VertexFormats.POSITION_TEXTURE
            );
            System.out.println("Shader loaded successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Error loading Doctor Strange shader", e);
        }
    }


    public static void render(MatrixStack matrices, double x, double y, double z, float tickDelta, float time) {
        MinecraftClient client = MinecraftClient.getInstance();

        matrices.push();
        matrices.translate(x, y + 1.6, z);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);

        RenderSystem.setShader(() -> shader);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT);

        float radius = 2.0f;
        int segments = 64;
        float r = 1.0f, g = 0.8f, b = 0.4f, a = 1.0f;
        int light = 0x00F000F0;

        buffer.vertex(matrix, 0, 0, 0).color(r, g, b, a).texture(0.5f, 0.5f).light(light).next();

        for (int i = 0; i <= segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float dx = (float) Math.cos(angle) * radius;
            float dy = (float) Math.sin(angle) * radius;

            float u = 0.5f + dx / (radius * 2);
            float v = 0.5f + dy / (radius * 2);

            buffer.vertex(matrix, dx, dy, 0).color(r, g, b, a).texture(u, v).light(light).next();
        }

        tessellator.draw();

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        matrices.pop();
    }

}
