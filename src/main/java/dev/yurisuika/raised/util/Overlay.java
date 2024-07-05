package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.List;

public class Overlay {

    public static List<RenderGameOverlayEvent.ElementType> hotbar = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.HOTBAR,
            RenderGameOverlayEvent.ElementType.HEALTH,
            RenderGameOverlayEvent.ElementType.ARMOR,
            RenderGameOverlayEvent.ElementType.FOOD,
            RenderGameOverlayEvent.ElementType.HEALTHMOUNT,
            RenderGameOverlayEvent.ElementType.AIR,
            RenderGameOverlayEvent.ElementType.JUMPBAR,
            RenderGameOverlayEvent.ElementType.EXPERIENCE
    );
    public static List<RenderGameOverlayEvent.ElementType> chat = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.CHAT
    );
    public static List<RenderGameOverlayEvent.ElementType> bossbar = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.BOSSHEALTH,
            RenderGameOverlayEvent.ElementType.BOSSINFO
    );
    public static List<RenderGameOverlayEvent.ElementType> sidebar = Lists.newArrayList(
    );
    public static List<RenderGameOverlayEvent.ElementType> effects = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.POTION_ICONS
    );
    public static List<RenderGameOverlayEvent.ElementType> players = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.PLAYER_LIST
    );
    public static List<RenderGameOverlayEvent.ElementType> toasts = Lists.newArrayList(
    );
    public static List<RenderGameOverlayEvent.ElementType> other = Lists.newArrayList(
            RenderGameOverlayEvent.ElementType.ALL
    );

    public static List<RenderGameOverlayEvent.ElementType> getHotbar() {
        return hotbar;
    }

    public static List<RenderGameOverlayEvent.ElementType> getChat() {
        return chat;
    }

    public static List<RenderGameOverlayEvent.ElementType> getBossbar() {
        return bossbar;
    }

    public static List<RenderGameOverlayEvent.ElementType> getSidebar() {
        return sidebar;
    }

    public static List<RenderGameOverlayEvent.ElementType> getEffects() {
        return effects;
    }

    public static List<RenderGameOverlayEvent.ElementType> getPlayers() {
        return players;
    }

    public static List<RenderGameOverlayEvent.ElementType> getToasts() {
        return toasts;
    }

    public static List<RenderGameOverlayEvent.ElementType> getOther() {
        return other;
    }

}