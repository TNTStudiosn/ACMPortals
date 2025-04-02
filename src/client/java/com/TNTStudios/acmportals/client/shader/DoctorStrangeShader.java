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
        matrices.translate(x, y + 0.01, z); // Leve elevación para evitar z-fighting con el suelo
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        // Configuración del pipeline gráfico
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull(); // Desactiva el culling para ver ambos lados del portal
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);

        // Activamos el shader y le pasamos el uniform
        RenderSystem.setShader(() -> shader);
        shader.getUniform("time").set(time);

        // Comenzamos el renderizado
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);

        float size = 2.5f;

        buffer.vertex(matrix, -size, 0.0f, -size).texture(0.0f, 0.0f).next();
        buffer.vertex(matrix, -size, 0.0f,  size).texture(0.0f, 1.0f).next();
        buffer.vertex(matrix,  size, 0.0f,  size).texture(1.0f, 1.0f).next();
        buffer.vertex(matrix,  size, 0.0f, -size).texture(1.0f, 0.0f).next();

        tessellator.draw();

        // Restaurar estados gráficos
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        matrices.pop();
    }





}
