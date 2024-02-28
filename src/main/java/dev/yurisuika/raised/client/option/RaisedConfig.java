package dev.yurisuika.raised.client.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class RaisedConfig {

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Config config = new Config();

    public static class Config {

        public Value value = new Value(
                2,
                0
        );
        public Toggle toggle = new Toggle(
                true,
                false,
                false
        );

        public static class Value {

            public int hud;
            public int chat;

            public Value(int hud, int chat) {
                this.hud = hud;
                this.chat = chat;
            }

        }

        public static class Toggle {

            public boolean support;
            public boolean sync;
            public boolean texture;

            public Toggle(boolean support, boolean sync, boolean texture) {
                this.support = support;
                this.sync = sync;
                this.texture = texture;
            }

        }

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
                config = gson.fromJson(Files.readString(file.toPath()), Config.class);
            } else {
                config = new Config();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(Config config) {
        RaisedConfig.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setHud(int value) {
        config.value.hud = Math.max(value, 0);
        saveConfig();
    }

    public static void setChat(int value) {
        config.value.chat = Math.max(value, 0);
        saveConfig();
    }

    public static void setSupport(boolean value) {
        config.toggle.support = value;
        saveConfig();
    }

    public static void setSync(boolean value) {
        config.toggle.sync = value;
        saveConfig();
    }

    public static void setTexture(boolean value) {
        config.toggle.texture = value;
        saveConfig();
    }

    public static int getHud() {
        return config.value.hud;
    }

    public static int getChat() {
        return config.value.chat;
    }

    public static boolean getSupport() {
        return config.toggle.support;
    }

    public static boolean getSync() {
        return config.toggle.sync;
    }

    public static boolean getTexture() {
        return config.toggle.texture;
    }

}