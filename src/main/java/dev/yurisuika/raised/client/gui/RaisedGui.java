package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.gui.overlay.VanillaGuiOverlay.*;

public class RaisedGui extends ForgeGui {

    public static List<Identifier> hud = Lists.newArrayList(
            HOTBAR.id(),
            PLAYER_HEALTH.id(),
            ARMOR_LEVEL.id(),
            FOOD_LEVEL.id(),
            AIR_LEVEL.id(),
            MOUNT_HEALTH.id(),
            JUMP_BAR.id(),
            EXPERIENCE_BAR.id(),
            ITEM_NAME.id(),
            RECORD_OVERLAY.id()
    );
    public static List<Identifier> chat = Lists.newArrayList(
            CHAT_PANEL.id()
    );
    public static List<Identifier> all = Lists.newArrayList(
            VIGNETTE.id(),
            SPYGLASS.id(),
            HELMET.id(),
            FROSTBITE.id(),
            PORTAL.id(),
            HOTBAR.id(),
            CROSSHAIR.id(),
            BOSS_EVENT_PROGRESS.id(),
            PLAYER_HEALTH.id(),
            ARMOR_LEVEL.id(),
            FOOD_LEVEL.id(),
            AIR_LEVEL.id(),
            MOUNT_HEALTH.id(),
            JUMP_BAR.id(),
            EXPERIENCE_BAR.id(),
            ITEM_NAME.id(),
            SLEEP_FADE.id(),
            POTION_ICONS.id(),
            DEBUG_TEXT.id(),
            FPS_GRAPH.id(),
            RECORD_OVERLAY.id(),
            TITLE_TEXT.id(),
            SUBTITLES.id(),
            SCOREBOARD.id(),
            CHAT_PANEL.id(),
            PLAYER_LIST.id()
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    // HUD
    // START TRANSLATION OF HUD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF HUD EVENTS (CANCELLED)
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay().id()) && event.isCanceled()) {
            event.getPoseStack().translate(0, +getHud(), 0);
        }
    }
    // END TRANSLATION OF HUD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Post event) {
        if (hud.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, +getHud(), 0);
        }
    }

    // CHAT
    // START TRANSLATION OF CHAT EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGuiOverlayEvent.Pre event) {
        if (chat.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, -getChat(), +300);
        }
    }
    // END TRANSLATION OF CHAT EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGuiOverlayEvent.Post event) {
        if (chat.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, +getChat(), -300);
        }
    }

    // PRE MOD
    // START TRANSLATION OF PRE MOD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPreModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (!all.contains(event.getOverlay().id()) && getSupport().pre) {
            event.getPoseStack().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF PRE MOD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPreModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (!all.contains(event.getOverlay().id()) && getSupport().pre) {
            event.getPoseStack().translate(0, +getHud(), 0);
        }
    }

    // POST MOD
    // START TRANSLATION OF POST MOD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPostModTranslate(RenderGuiOverlayEvent.Post event) {
        if (!all.contains(event.getOverlay().id()) && getSupport().post) {
            event.getPoseStack().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF POST MOD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPostModTranslate(RenderGuiOverlayEvent.Post event) {
        if (!all.contains(event.getOverlay().id()) && getSupport().post) {
            event.getPoseStack().translate(0, +getHud(), 0);
        }
    }

}