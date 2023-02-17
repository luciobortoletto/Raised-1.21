package dev.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.yurisuika.raised.server.command.RaisedCommand;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

@Mod("raised")
public class Raised {

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Config config = new Config();

    public static class Config {

        public int hud = 2;
        public int chat = 0;

    }

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            if (file.exists()) {
                StringBuilder contentBuilder = new StringBuilder();
                try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                    stream.forEach(s -> contentBuilder.append(s).append("\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                config = gson.fromJson(contentBuilder.toString(), Config.class);
            } else {
                config = new Config();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(Config config) {
        Raised.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setHud(int value) {
        config.hud = value;
        saveConfig();
    }

    public static void setChat(int value) {
        config.chat = value;
        saveConfig();
    }

    public static int getHud() {
        return config.hud;
    }

    public static int getChat() {
        return config.chat;
    }

    public static final KeyBinding hudDown = new KeyBinding(
            "key.raised.hud.down",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.categories.raised"
    );
    public static final KeyBinding hudUp = new KeyBinding(
            "key.raised.hud.up",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_ADD,
            "key.categories.raised"
    );
    public static final KeyBinding chatDown = new KeyBinding(
            "key.raised.chat.down",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_DIVIDE,
            "key.categories.raised"
    );
    public static final KeyBinding chatUp = new KeyBinding(
            "key.raised.chat.up",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_MULTIPLY,
            "key.categories.raised"
    );

    @Mod.EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (hudDown.wasPressed()) {
                Raised.setHud(config.hud - 1);
            }
            if (hudUp.wasPressed()) {
                Raised.setHud(config.hud + 1);
            }
            if (chatDown.wasPressed()) {
                Raised.setChat(config.chat - 1);
            }
            if (chatUp.wasPressed()) {
                Raised.setChat(config.chat + 1);
            }
        }

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event) {
            RaisedCommand.register(event.getDispatcher());
        }

    }

    @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ClientRegistry.registerKeyBinding(hudDown);
            ClientRegistry.registerKeyBinding(hudUp);
            ClientRegistry.registerKeyBinding(chatDown);
            ClientRegistry.registerKeyBinding(chatUp);
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