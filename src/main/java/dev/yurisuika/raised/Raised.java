package dev.yurisuika.raised;

import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.util.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.network.chat.TranslatableComponent;

public class Raised implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public static void registerClientTickEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            while (RaisedOptions.options.consumeClick()) {
                minecraft.setScreen(new RaisedScreen(new TranslatableComponent("options.raised.title")));
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerCommands() {
        RaisedCommand.register(ClientCommandManager.DISPATCHER);
    }

    @Environment(EnvType.CLIENT)
    public static void registerKeyBindings() {
        KeyBindingHelper.registerKeyBinding(RaisedOptions.options);
    }

    @Override
    public void onInitializeClient() {
        Config.loadConfig();

        registerClientTickEvents();
        registerCommands();
        registerKeyBindings();
    }

}