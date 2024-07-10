package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.List;

public class Overlay {

    public static List<ResourceLocation> hotbar = Lists.newArrayList(
            VanillaGuiLayers.HOTBAR,
            VanillaGuiLayers.PLAYER_HEALTH,
            VanillaGuiLayers.ARMOR_LEVEL,
            VanillaGuiLayers.FOOD_LEVEL,
            VanillaGuiLayers.AIR_LEVEL,
            VanillaGuiLayers.VEHICLE_HEALTH,
            VanillaGuiLayers.JUMP_METER,
            VanillaGuiLayers.EXPERIENCE_BAR,
            VanillaGuiLayers.EXPERIENCE_LEVEL,
            VanillaGuiLayers.SELECTED_ITEM_NAME,
            VanillaGuiLayers.SPECTATOR_TOOLTIP,
            VanillaGuiLayers.OVERLAY_MESSAGE
    );
    public static List<ResourceLocation> chat = Lists.newArrayList(
            VanillaGuiLayers.CHAT
    );
    public static List<ResourceLocation> bossbar = Lists.newArrayList(
            VanillaGuiLayers.BOSS_OVERLAY
    );
    public static List<ResourceLocation> sidebar = Lists.newArrayList(
            VanillaGuiLayers.SCOREBOARD_SIDEBAR
    );
    public static List<ResourceLocation> effects = Lists.newArrayList(
            VanillaGuiLayers.EFFECTS
    );
    public static List<ResourceLocation> players = Lists.newArrayList(
            VanillaGuiLayers.TAB_LIST
    );
    public static List<ResourceLocation> toasts = Lists.newArrayList(
    );
    public static List<ResourceLocation> other = Lists.newArrayList(
    );

    public static List<ResourceLocation> getHotbar() {
        return hotbar;
    }

    public static List<ResourceLocation> getChat() {
        return chat;
    }

    public static List<ResourceLocation> getBossbar() {
        return bossbar;
    }

    public static List<ResourceLocation> getSidebar() {
        return sidebar;
    }

    public static List<ResourceLocation> getEffects() {
        return effects;
    }

    public static List<ResourceLocation> getPlayers() {
        return players;
    }

    public static List<ResourceLocation> getToasts() {
        return toasts;
    }

    public static List<ResourceLocation> getOther() {
        return other;
    }

}