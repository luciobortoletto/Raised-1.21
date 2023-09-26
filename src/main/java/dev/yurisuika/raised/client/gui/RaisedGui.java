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
    
    private final List<RenderGameOverlayEvent.ElementType> hud = Lists.newArrayList(
            ARMOR,
            HEALTH,
            FOOD,
            AIR,
            HOTBAR,
            EXPERIENCE,
            TEXT,
            HEALTHMOUNT,
            JUMPBAR
    );
    private final List<RenderGameOverlayEvent.ElementType> chat = Lists.newArrayList(
            CHAT
    );
    private final List<RenderGameOverlayEvent.ElementType> other = Lists.newArrayList(
            ALL
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    // START TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void translatePreHighest(RenderGameOverlayEvent.Pre event) {
        if (other.contains(event.getType()) && getSupport().pre) {
            RenderSystem.translatef(0, -getHud(), 0);
        } else if (hud.contains(event.getType())) {
            RenderSystem.translatef(0, -getHud(), 0);
        } else if (chat.contains(event.getType())) {
            RenderSystem.translatef(0, -getChat(), +300);
        }
    }

    // START TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void translatePostHighest(RenderGameOverlayEvent.Post event) {
        if (other.contains(event.getType()) && getSupport().post) {
            RenderSystem.translatef(0, -getHud(), 0);
        }
    }

    // END TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void translatePreLowest(RenderGameOverlayEvent.Pre event) {
        if (other.contains(event.getType()) && getSupport().pre) {
            RenderSystem.translatef(0, +getHud(), 0);
        } else if (hud.contains(event.getType()) && event.isCanceled()) {
            RenderSystem.translatef(0, +getHud(), 0);
        }
    }

    // END TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void translatePostLowest(RenderGameOverlayEvent.Post event) {
        if (other.contains(event.getType()) && getSupport().post) {
            RenderSystem.translatef(0, +getHud(), 0);
        } else if (hud.contains(event.getType())) {
            RenderSystem.translatef(0, +getHud(), 0);
        } else if (chat.contains(event.getType())) {
            RenderSystem.translatef(0, +getChat(), -300);
        }
    }

}