package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.*;

public class RaisedGui extends ForgeIngameGui {

    public boolean translated = false;

    public static List<IIngameOverlay> hud = Lists.newArrayList(
            HOTBAR_ELEMENT,
            PLAYER_HEALTH_ELEMENT,
            ARMOR_LEVEL_ELEMENT,
            FOOD_LEVEL_ELEMENT,
            MOUNT_HEALTH_ELEMENT,
            AIR_LEVEL_ELEMENT,
            JUMP_BAR_ELEMENT,
            EXPERIENCE_BAR_ELEMENT,
            ITEM_NAME_ELEMENT,
            RECORD_OVERLAY_ELEMENT
    );
    public static List<IIngameOverlay> chat = Lists.newArrayList(
            CHAT_PANEL_ELEMENT
    );
    public static List<RenderGameOverlayEvent.ElementType> all = Lists.newArrayList(
            ALL
    );
    public static List<IIngameOverlay> modHud = Lists.newArrayList(
    );
    public static List<IIngameOverlay> modChat = Lists.newArrayList(
    );
    public static List<IIngameOverlay> modAll = Lists.newArrayList(
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    public void start(RenderGameOverlayEvent event, int x, int y, int z) {
        if (!translated) {
            translated = true;
            event.getMatrixStack().push();
            event.getMatrixStack().translate(x, -y, +z);
        }
    }

    public void end(RenderGameOverlayEvent event) {
        if (translated) {
            translated = false;
            event.getMatrixStack().pop();
        }
    }

    // HUD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (hud.contains(event.getOverlay())) {
            start(event, 0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (hud.contains(event.getOverlay()) && event.isCanceled()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endHudTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (hud.contains(event.getOverlay())) {
            end(event);
        }
    }

    // CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (chat.contains(event.getOverlay())) {
            start(event, 0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (chat.contains(event.getOverlay()) && event.isCanceled()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endChatTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (chat.contains(event.getOverlay())) {
            end(event);
        }
    }

    // ALL (PRE)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            start(event, 0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            end(event);
        }
    }

    // ALL (POST)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startAllPostTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            start(event, 0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endAllPostTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            end(event);
        }
    }

    // MOD HUD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modHud.contains(event.getOverlay())) {
            start(event, 0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modHud.contains(event.getOverlay()) && event.isCanceled()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endModHudTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (modHud.contains(event.getOverlay())) {
            end(event);
        }
    }

    // MOD CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modChat.contains(event.getOverlay())) {
            start(event, 0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modChat.contains(event.getOverlay()) && event.isCanceled()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endModChatTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (modChat.contains(event.getOverlay())) {
            end(event);
        }
    }

    // MOD CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modChat.contains(event.getOverlay())) {
            start(event, 0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModChatTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modChat.contains(event.getOverlay()) && event.isCanceled()) {
            end(event, 0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endModChatTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (modChat.contains(event.getOverlay())) {
            end(event, 0, getSync() ? getHud() : getChat(), 300);
        }
    }

    // MOD ALL
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModAllTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modAll.contains(event.getOverlay()) && getSupport()) {
            start(event, 0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModAllTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (modAll.contains(event.getOverlay()) && event.isCanceled() && getSupport()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endModAllTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (modAll.contains(event.getOverlay()) && getSupport()) {
            end(event);
        }
    }

}