package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.RaisedGui;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod("raised")
public class Raised {

    @Mod.EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void keyInput(InputEvent.Key event) {
            while (RaisedOptions.options.consumeClick()) {
                Minecraft.getInstance().setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
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
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Hotbar());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Chat());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Bossbar());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Sidebar());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Effects());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Players());
            MinecraftForge.EVENT_BUS.register(new RaisedGui.Other());
        }

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(RaisedOptions.options);
        }

    }

    public Raised() {
        Config.loadConfig();

        MinecraftForge.EVENT_BUS.register(this);
    }

}