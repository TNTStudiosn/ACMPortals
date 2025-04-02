package com.TNTStudios.acmportals.command;

import com.TNTStudios.acmportals.portal.PortalManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import static net.minecraft.server.command.CommandManager.literal;

public class ViewPortalCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("acm")
                .then(literal("view")
                        .then(net.minecraft.server.command.CommandManager.argument("name", StringArgumentType.word())
                                .executes(ctx -> {
                                    String name = StringArgumentType.getString(ctx, "name");
                                    PortalManager.spawnPortal(ctx.getSource(), name);
                                    return 1;
                                }))));
    }
}
