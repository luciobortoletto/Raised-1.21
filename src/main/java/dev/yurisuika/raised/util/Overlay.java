package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

import java.util.List;

public class Overlay {

    public static List<IIngameOverlay> hotbar = Lists.newArrayList(
            ForgeIngameGui.HOTBAR_ELEMENT,
            ForgeIngameGui.PLAYER_HEALTH_ELEMENT,
            ForgeIngameGui.ARMOR_LEVEL_ELEMENT,
            ForgeIngameGui.FOOD_LEVEL_ELEMENT,
            ForgeIngameGui.MOUNT_HEALTH_ELEMENT,
            ForgeIngameGui.AIR_LEVEL_ELEMENT,
            ForgeIngameGui.JUMP_BAR_ELEMENT,
            ForgeIngameGui.EXPERIENCE_BAR_ELEMENT,
            ForgeIngameGui.ITEM_NAME_ELEMENT,
            ForgeIngameGui.RECORD_OVERLAY_ELEMENT
    );
    public static List<IIngameOverlay> chat = Lists.newArrayList(
            ForgeIngameGui.CHAT_PANEL_ELEMENT
    );
    public static List<IIngameOverlay> bossbar = Lists.newArrayList(
            ForgeIngameGui.BOSS_HEALTH_ELEMENT
    );
    public static List<IIngameOverlay> sidebar = Lists.newArrayList(
            ForgeIngameGui.SCOREBOARD_ELEMENT
    );
    public static List<IIngameOverlay> effects = Lists.newArrayList(
            ForgeIngameGui.POTION_ICONS_ELEMENT
    );
    public static List<IIngameOverlay> players = Lists.newArrayList(
            ForgeIngameGui.PLAYER_LIST_ELEMENT
    );
    public static List<IIngameOverlay> toasts = Lists.newArrayList(
    );
    public static List<IIngameOverlay> other = Lists.newArrayList(
    );

    public static List<IIngameOverlay> getHotbar() {
        return hotbar;
    }

    public static List<IIngameOverlay> getChat() {
        return chat;
    }

    public static List<IIngameOverlay> getBossbar() {
        return bossbar;
    }

    public static List<IIngameOverlay> getSidebar() {
        return sidebar;
    }

    public static List<IIngameOverlay> getEffects() {
        return effects;
    }

    public static List<IIngameOverlay> getPlayers() {
        return players;
    }

    public static List<IIngameOverlay> getToasts() {
        return toasts;
    }

    public static List<IIngameOverlay> getOther() {
        return other;
    }

}