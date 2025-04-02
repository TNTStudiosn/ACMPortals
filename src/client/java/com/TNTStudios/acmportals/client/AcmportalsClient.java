package com.TNTStudios.acmportals.client;

import com.TNTStudios.acmportals.client.network.ClientPacketHandler;
import com.TNTStudios.acmportals.client.shader.DoctorStrangeShader;
import com.TNTStudios.acmportals.client.shader.DoctorStrangeShaderLoader;
import net.fabricmc.api.ClientModInitializer;

public class AcmportalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DoctorStrangeShaderLoader.register();
        PortalRenderHandler.init();
        ClientPacketHandler.register();
    }
}
