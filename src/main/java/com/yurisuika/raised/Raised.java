package com.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

@Mod("raised")
public class Raised {

    private static final Logger LOGGER = LogManager.getLogger();

    public static KeyBinding down;
    public static KeyBinding up;
    public static KeyBinding offsetDown;
    public static KeyBinding offsetUp;

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static RaisedConfig config = new RaisedConfig();

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        }
        catch (Exception e) {
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
    }

    public static void setOffset(int change) {
        config.offset += change;
        saveConfig();
    }

    public static int getDistance() {
        return config.distance;
    }

    public static int getOffset() {
        return config.offset;
    }

    public void input(InputEvent.KeyInputEvent event){
        if (down.consumeClick()) {
            setDistance(-1);
        }
        if (up.consumeClick()) {
            setDistance(1);
        }
        if (offsetDown.consumeClick()) {
            setOffset(-1);
        }
        if (offsetUp.consumeClick()) {
            setOffset(1);
        }
    }

    public Raised()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLClientSetupEvent event) {
        LOGGER.info("Loading Raised!");
        MinecraftForge.EVENT_BUS.addListener(this::input);

        loadConfig();

        down = new KeyBinding("raised.down", KeyConflictContext.IN_GAME, InputMappings.getKey("key.keyboard.minus"), "raised.title");
        up = new KeyBinding("raised.up", KeyConflictContext.IN_GAME, InputMappings.getKey("key.keyboard.equal"), "raised.title");
        offsetDown = new KeyBinding("raised.offset.down", KeyConflictContext.IN_GAME, InputMappings.getKey("key.keyboard.left.bracket"), "raised.title");
        offsetUp = new KeyBinding("raised.offset.up", KeyConflictContext.IN_GAME, InputMappings.getKey("key.keyboard.right.bracket"), "raised.title");

        ClientRegistry.registerKeyBinding(down);
        ClientRegistry.registerKeyBinding(up);
        ClientRegistry.registerKeyBinding(offsetDown);
        ClientRegistry.registerKeyBinding(offsetUp);
    }

}