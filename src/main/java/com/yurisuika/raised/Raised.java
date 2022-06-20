package com.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Raised implements ClientModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();

    private static final KeyBinding down = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.down",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_MINUS,
            "raised.title"
    ));

    private static final KeyBinding up = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.up",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_EQUAL,
            "raised.title"
    ));

    private static final KeyBinding offsetDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.offset.down",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_BRACKET,
            "raised.title"
    ));

    private static final KeyBinding offsetUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.offset.up",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_BRACKET,
            "raised.title"
    ));

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static RaisedConfig config = new RaisedConfig();

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void loadConfig() {
        try {
            if (file.exists()) {
                StringBuilder contentBuilder = new StringBuilder();

                try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8))
                {
                    stream.forEach(s -> contentBuilder.append(s).append("\n"));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                config = gson.fromJson(contentBuilder.toString(), RaisedConfig.class);
            } else {
                config = new RaisedConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(RaisedConfig config) {
        Raised.config = config;
    }

    public static RaisedConfig getConfig() {
        return config;
    }

    public static void setDistance(int change) {
        config.distance += change;
        saveConfig();
        putObjects();
    }

    public static void setOffset(int change) {
        config.offset += change;
        saveConfig();
        putObjects();
    }

    public static int getDistance() {
        return config.distance;
    }

    public static int getOffset() {
        return config.offset;
    }

    public static void putObjects() {
        FabricLoader.getInstance().getObjectShare().put("raised:distance", config.distance);
        FabricLoader.getInstance().getObjectShare().put("raised:offset", config.offset);
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Loading Raised!");

        loadConfig();
        putObjects();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (down.wasPressed()) {
                setDistance(-1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (up.wasPressed()) {
                setDistance(1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (offsetDown.wasPressed()) {
                setOffset(-1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (offsetUp.wasPressed()) {
                setOffset(1);
            }
        });
    }

}