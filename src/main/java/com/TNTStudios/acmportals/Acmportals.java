package com.TNTStudios.acmportals;

import com.TNTStudios.acmportals.command.RemovePortalCommand;
import com.TNTStudios.acmportals.command.ViewPortalCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Acmportals implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, env) -> {
            ViewPortalCommand.register(dispatcher);
            RemovePortalCommand.register(dispatcher);
        });
    }

}
