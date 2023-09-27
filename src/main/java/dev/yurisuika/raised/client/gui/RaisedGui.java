package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.gui.overlay.VanillaGuiOverlay.*;

public class RaisedGui extends ForgeGui {

    public final List<NamedGuiOverlay> hud = Lists.newArrayList(
            HOTBAR.type(),
            PLAYER_HEALTH.type(),
            ARMOR_LEVEL.type(),
            FOOD_LEVEL.type(),
            AIR_LEVEL.type(),
            MOUNT_HEALTH.type(),
            JUMP_BAR.type(),
            EXPERIENCE_BAR.type(),
            ITEM_NAME.type(),
            RECORD_OVERLAY.type()
    );
    public final List<NamedGuiOverlay> chat = Lists.newArrayList(
            CHAT_PANEL.type()
    );
    public final List<NamedGuiOverlay> all = Lists.newArrayList(
            VIGNETTE.type(),
            SPYGLASS.type(),
            HELMET.type(),
            FROSTBITE.type(),
            PORTAL.type(),
            HOTBAR.type(),
            CROSSHAIR.type(),
            BOSS_EVENT_PROGRESS.type(),
            PLAYER_HEALTH.type(),
            ARMOR_LEVEL.type(),
            FOOD_LEVEL.type(),
            AIR_LEVEL.type(),
            MOUNT_HEALTH.type(),
            JUMP_BAR.type(),
            EXPERIENCE_BAR.type(),
            ITEM_NAME.type(),
            SLEEP_FADE.type(),
            POTION_ICONS.type(),
            DEBUG_TEXT.type(),
            FPS_GRAPH.type(),
            RECORD_OVERLAY.type(),
            TITLE_TEXT.type(),
            SUBTITLES.type(),
            SCOREBOARD.type(),
            CHAT_PANEL.type(),
            PLAYER_LIST.type()
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    // HUD
    // START TRANSLATION OF HUD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF HUD EVENTS (CANCELLED)
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay()) && event.isCanceled()) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        }
    }
    // END TRANSLATION OF HUD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Post event) {
        if (hud.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        }
    }

    // CHAT
    // START TRANSLATION OF CHAT EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGuiOverlayEvent.Pre event) {
        if (chat.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, -getChat(), +300);
        }
    }
    // END TRANSLATION OF CHAT EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGuiOverlayEvent.Pre event) {
        if (chat.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, +getChat(), -300);
        }
    }

    // PRE MOD
    // START TRANSLATION OF PRE MOD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPreModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (!all.contains(event.getOverlay()) && getSupport().pre) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF PRE MOD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPreModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (!all.contains(event.getOverlay()) && getSupport().pre) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        }
    }

    // POST MOD
    // START TRANSLATION OF POST MOD EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPostModTranslate(RenderGuiOverlayEvent.Post event) {
        if (!all.contains(event.getOverlay()) && getSupport().post) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        }
    }
    // END TRANSLATION OF POST MOD EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPostModTranslate(RenderGuiOverlayEvent.Post event) {
        if (!all.contains(event.getOverlay()) && getSupport().post) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        }
    }

}