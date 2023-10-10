package dev.yurisuika.raised.client.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public class RaisedConfig {

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Config config = new Config();

    public static class Config {

        public Value value = new Value(
                2,
                0
        );
        public Toggle toggle = new Toggle(
                true,
                true,
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

            public boolean share;
            public boolean support;
            public boolean sync;

            public Toggle(boolean share, boolean support, boolean sync) {
                this.share = share;
                this.support = support;
                this.sync = sync;
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
                StringBuilder contentBuilder = new StringBuilder();
                try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                    stream.forEach(s -> contentBuilder.append(s).append("\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                config = gson.fromJson(contentBuilder.toString(), RaisedConfig.Config.class);
            } else {
                config = new RaisedConfig.Config();
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
        putObjects();
    }

    public static void setChat(int value) {
        config.value.chat = Math.max(value, 0);
        saveConfig();
        putObjects();
    }

    public static void setShare(boolean value) {
        config.toggle.share = value;
        saveConfig();
        putObjects();
    }

    public static void setSupport(boolean value) {
        config.toggle.support = value;
        saveConfig();
        putObjects();
    }

    public static void setSync(boolean value) {
        config.toggle.sync = value;
        saveConfig();
    }

    public static int getHud() {
        return config.value.hud;
    }

    public static int getChat() {
        if (getSync()) {
            return config.value.hud;
        } else {
            return config.value.chat;
        }
    }

    public static boolean getShare() {
        return config.toggle.share;
    }


    public static boolean getSupport() {
        return config.toggle.support;
    }

    public static boolean getSync() {
        return config.toggle.sync;
    }

    public static void putObjects() {
        FabricLoader.getInstance().getObjectShare().put("raised:hud", getShare() ? getHud() : 0);
        FabricLoader.getInstance().getObjectShare().put("raised:chat", getShare() ? getChat() : 0);
    }

}