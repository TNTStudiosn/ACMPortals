package com.TNTStudios.acmportals.client.shader;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class DoctorStrangeShaderLoader implements SimpleSynchronousResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        // Un identificador único para este listener
        return new Identifier("acmportals", "doctorstrange_shader_loader");
    }

    @Override
    public void reload(ResourceManager manager) {
        // Aquí se carga el shader cuando ya el ResourceManager está listo
        DoctorStrangeShader.init();
    }

    // Método para registrar el listener
    public static void register() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
                .registerReloadListener(new DoctorStrangeShaderLoader());
    }
}
