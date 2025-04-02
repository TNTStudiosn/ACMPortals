package com.TNTStudios.acmportals.portal;

import net.minecraft.util.math.Vec3d;

public class PortalInstance {
    private final String name;
    private final Vec3d position;

    public PortalInstance(String name, Vec3d position) {
        this.name = name;
        this.position = position;
    }

    public void spawn() {
        // Cliente recibirá info y renderizará shader
    }

    public void remove() {
        // Enviar evento de desaparición al cliente
    }

    public Vec3d getPosition() {
        return position;
    }
}
