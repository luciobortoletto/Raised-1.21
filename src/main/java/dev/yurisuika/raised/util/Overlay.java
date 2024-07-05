package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

import java.util.List;

public class Overlay {

    public static List<ResourceLocation> hotbar = Lists.newArrayList(
            VanillaGuiOverlay.HOTBAR.id(),
            VanillaGuiOverlay.PLAYER_HEALTH.id(),
            VanillaGuiOverlay.ARMOR_LEVEL.id(),
            VanillaGuiOverlay.FOOD_LEVEL.id(),
            VanillaGuiOverlay.AIR_LEVEL.id(),
            VanillaGuiOverlay.MOUNT_HEALTH.id(),
            VanillaGuiOverlay.JUMP_BAR.id(),
            VanillaGuiOverlay.EXPERIENCE_BAR.id(),
            VanillaGuiOverlay.ITEM_NAME.id(),
            VanillaGuiOverlay.RECORD_OVERLAY.id()
    );
    public static List<ResourceLocation> chat = Lists.newArrayList(
            VanillaGuiOverlay.CHAT_PANEL.id()
    );
    public static List<ResourceLocation> bossbar = Lists.newArrayList(
            VanillaGuiOverlay.BOSS_EVENT_PROGRESS.id()
    );
    public static List<ResourceLocation> sidebar = Lists.newArrayList(
            VanillaGuiOverlay.SCOREBOARD.id()
    );
    public static List<ResourceLocation> effects = Lists.newArrayList(
            VanillaGuiOverlay.POTION_ICONS.id()
    );
    public static List<ResourceLocation> players = Lists.newArrayList(
            VanillaGuiOverlay.PLAYER_LIST.id()
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