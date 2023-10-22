package dev.yurisuika.raised;

import dev.yurisuika.raised.client.gui.screen.RaisedScreen;
import dev.yurisuika.raised.server.command.RaisedCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.TranslatableText;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static dev.yurisuika.raised.client.option.RaisedKeyBinding.*;

public class Raised implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public static void registerClientTickEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (options.wasPressed()) {
                client.openScreen(new RaisedScreen(new TranslatableText("options.raised.title")));
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerCommands() {
        RaisedCommand.register(ClientCommandManager.DISPATCHER);
    }

    @Override
    public void onInitializeClient() {
        if (!file.exists()) {
            saveConfig();
        }
        loadConfig();
        putObjects();

        registerClientTickEvents();
        registerCommands();
        registerKeyBindings();
    }

}