package dev.yurisuika.raised;

import dev.yurisuika.raised.client.gui.RaisedGui;
import dev.yurisuika.raised.server.command.RaisedCommand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static dev.yurisuika.raised.client.option.RaisedKeyBinding.*;

@Mod("raised")
public class Raised {

    @Mod.EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void keyInput(InputEvent.Key event) {
            if (hudDown.wasPressed()) {
                setHud(config.value.hud - 1);
            }
            if (hudReset.wasPressed()) {
                setHud(2);
            }
            if (hudUp.wasPressed()) {
                setHud(config.value.hud + 1);
            }
            if (chatDown.wasPressed()) {
                setChat(config.value.chat - 1);
            }
            if (chatReset.wasPressed()) {
                setChat(0);
            }
            if (chatUp.wasPressed()) {
                setChat(config.value.chat + 1);
            }
        }

        @SubscribeEvent
        public static void registerClientCommands(RegisterClientCommandsEvent event) {
            RaisedCommand.register(event.getDispatcher(), event.getBuildContext());
        }

    }

    @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            MinecraftForge.EVENT_BUS.register(new RaisedGui());
        }

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(hudDown);
            event.register(hudReset);
            event.register(hudUp);
            event.register(chatDown);
            event.register(chatReset);
            event.register(chatUp);
        }

    }

    public Raised() {
        if (!file.exists()) {
            saveConfig();
        }
        loadConfig();

        MinecraftForge.EVENT_BUS.register(this);
    }

}