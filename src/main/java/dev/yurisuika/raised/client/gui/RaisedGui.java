package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.*;

public class RaisedGui extends ForgeIngameGui {

    public boolean translated = false;

    public static List<RenderGameOverlayEvent.ElementType> hud = Lists.newArrayList(
            ARMOR,
            HEALTH,
            FOOD,
            AIR,
            HOTBAR,
            EXPERIENCE,
            HEALTHMOUNT,
            JUMPBAR
    );
    public static List<RenderGameOverlayEvent.ElementType> chat = Lists.newArrayList(
            CHAT
    );
    public static List<RenderGameOverlayEvent.ElementType> all = Lists.newArrayList(
            ALL
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    public void start(int x, int y, int z) {
        if(!translated) {
            translated = true;
            RenderSystem.translatef(x, -y, +z);
        }
    }

    public void end(int x, int y, int z) {
        if(translated) {
            translated = false;
            RenderSystem.translatef(x, +y, -z);
        }
    }

    // HUD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGameOverlayEvent.Pre event) {
        if (hud.contains(event.getType())) {
            start(0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGameOverlayEvent.Pre event) {
        if (hud.contains(event.getType()) && event.isCanceled()) {
            end(0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endHudTranslate(RenderGameOverlayEvent.Post event) {
        if (hud.contains(event.getType())) {
            end(0, getHud(), 0);
        }
    }

    // CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGameOverlayEvent.Pre event) {
        if (chat.contains(event.getType())) {
            start(0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGameOverlayEvent.Pre event) {
        if (chat.contains(event.getType()) && event.isCanceled()) {
            end(0, getSync() ? getHud() : getChat(), 300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endChatTranslate(RenderGameOverlayEvent.Post event) {
        if (chat.contains(event.getType())) {
            end(0, getSync() ? getHud() : getChat(), 300);
        }
    }

    // PRE MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startPreModTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            start(0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endPreModTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            end(0, getHud(), 0);
        }
    }

    // POST MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startPostModTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            start(0, getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endPostModTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            end(0, getHud(), 0);
        }
    }

}