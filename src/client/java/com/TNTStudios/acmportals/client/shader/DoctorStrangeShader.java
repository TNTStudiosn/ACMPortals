package com.TNTStudios.acmportals.client.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import net.minecraft.client.gl.ShaderProgram;

public class DoctorStrangeShader {
    public static ShaderProgram shader;

    public static void init() {
        try {
            String shaderId = "acmportals:shaders/core/doctorstrange";
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
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        RenderSystem.enableBlend();
        RenderSystem.setShader(() -> shader);
        shader.getUniform("time").set(time);

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);

        float size = 2.5f;
        buffer.vertex(matrix, -size, 0, -size).texture(0, 0).next();
        buffer.vertex(matrix, -size, 0,  size).texture(0, 1).next();
        buffer.vertex(matrix,  size, 0,  size).texture(1, 1).next();
        buffer.vertex(matrix,  size, 0, -size).texture(1, 0).next();

        BufferRenderer.draw(buffer.end());

        RenderSystem.disableBlend();
    }

}
