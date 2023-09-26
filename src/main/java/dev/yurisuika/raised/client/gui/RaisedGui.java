package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.gui.overlay.VanillaGuiOverlay.*;

public class RaisedGui extends ForgeGui {

    private static final Identifier WIDGETS = new Identifier("textures/gui/widgets.png");
    
    private final List<NamedGuiOverlay> hud = Lists.newArrayList(
            HOTBAR.type(),
            PLAYER_HEALTH.type(),
            ARMOR_LEVEL.type(),
            FOOD_LEVEL.type(),
            AIR_LEVEL.type(),
            MOUNT_HEALTH.type(),
            JUMP_BAR.type(),
            EXPERIENCE_BAR.type(),
            EXPERIENCE_BAR.type(),
            ITEM_NAME.type(),
            RECORD_OVERLAY.type()
    );

    private final List<NamedGuiOverlay> chat = Lists.newArrayList(
            CHAT_PANEL.type()
    );

    private final List<String> mods = Lists.newArrayList(
            "colorfulhearts:health"
    );

    private final List<NamedGuiOverlay> other = Lists.newArrayList(
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

    // START TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void translatePreHighest(RenderGuiOverlayEvent.Pre event) {

        if (!other.contains(event.getOverlay()) && getSupport().pre) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        } else if (hud.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        } else if (chat.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, -getChat(), +300);
        }

        if (mods.contains(event.getOverlay().id().toString())) {
//            event.getGuiGraphics().getMatrices().translate(0, -getChat(), +300);
        }

    }

    // START TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void translatePostHighest(RenderGuiOverlayEvent.Post event) {

        if (!other.contains(event.getOverlay()) && getSupport().post) {
            event.getGuiGraphics().getMatrices().translate(0, -getHud(), 0);
        }

    }

    // END TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void translatePreLowest(RenderGuiOverlayEvent.Pre event) {

        if (!other.contains(event.getOverlay()) && getSupport().pre) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        } else if (hud.contains(event.getOverlay()) && event.isCanceled()) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        }

    }

    // END TRANSLATION OF EVENTS
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void translatePostLowest(RenderGuiOverlayEvent.Post event) {

        if (!other.contains(event.getOverlay()) && getSupport().post) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        } else if (hud.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, +getHud(), 0);
        } else if (chat.contains(event.getOverlay())) {
            event.getGuiGraphics().getMatrices().translate(0, +getChat(), -300);
        }

        if (mods.contains(event.getOverlay().id().toString())) {
//            event.getGuiGraphics().getMatrices().translate(0, +getChat(), +300);
        }

    }

}