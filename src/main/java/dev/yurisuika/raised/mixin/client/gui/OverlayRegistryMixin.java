package dev.yurisuika.raised.mixin.client.gui;

import com.google.common.collect.Lists;
import dev.yurisuika.raised.util.Overlay;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = OverlayRegistry.class, remap = false)
public abstract class OverlayRegistryMixin {

    @Unique
    private static final List<String> vanilla = Lists.newArrayList(
            "Vignette",
            "Spyglass",
            "Helmet",
            "Frostbite",
            "Portal",
            "Hotbar",
            "Crosshair",
            "Boss Health",
            "Player Health",
            "Armor Level",
            "Food Level",
            "Mount Health",
            "Air Level",
            "Jump Bar",
            "Experience Bar",
            "Item Name",
            "Sleep Fade",
            "Text Columns",
            "FPS Graph",
            "Potion Icons",
            "Record",
            "Subtitles",
            "Title Text",
            "Scoreboard",
            "Chat History",
            "Player List"
    );

    /**
     * Adds mods registered below all overlays to be moved.
     */
    @Inject(method = "registerOverlayBottom", at = @At("HEAD"))
    private static void addOverlayBelowAll(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (!vanilla.contains(displayName)) {
            Overlay.getOther().add(overlay);
        }
    }

    /**
     * Adds mods registered below certain overlays all to be moved.
     */
    @Inject(method = "registerOverlayBelow", at = @At("HEAD"))
    private static void addOverlayBelow(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (Overlay.getHotbar().contains(other)) {
            Overlay.getHotbar().add(overlay);
        } else if (Overlay.getChat().contains(other)) {
            Overlay.getChat().add(overlay);
        } else if (Overlay.getBossbar().contains(other)) {
            Overlay.getBossbar().add(overlay);
        } else if (Overlay.getSidebar().contains(other)) {
            Overlay.getSidebar().add(overlay);
        } else if (Overlay.getEffects().contains(other)) {
            Overlay.getEffects().add(overlay);
        } else if (Overlay.getPlayers().contains(other)) {
            Overlay.getPlayers().add(overlay);
        } else if (Overlay.getToasts().contains(other)) {
            Overlay.getToasts().add(overlay);
        }
    }

    /**
     * Adds mods registered above certain overlays all to be moved.
     */
    @Inject(method = "registerOverlayAbove", at = @At("HEAD"))
    private static void addOverlayAbove(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (Overlay.getHotbar().contains(other)) {
            Overlay.getHotbar().add(overlay);
        } else if (Overlay.getChat().contains(other)) {
            Overlay.getChat().add(overlay);
        } else if (Overlay.getBossbar().contains(other)) {
            Overlay.getBossbar().add(overlay);
        } else if (Overlay.getSidebar().contains(other)) {
            Overlay.getSidebar().add(overlay);
        } else if (Overlay.getEffects().contains(other)) {
            Overlay.getEffects().add(overlay);
        } else if (Overlay.getPlayers().contains(other)) {
            Overlay.getPlayers().add(overlay);
        } else if (Overlay.getToasts().contains(other)) {
            Overlay.getToasts().add(overlay);
        }
    }

    /**
     * Adds mods registered above all overlays to be moved.
     */
    @Inject(method = "registerOverlayTop", at = @At("HEAD"))
    private static void addOverlayAboveAll(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (!vanilla.contains(displayName)) {
            Overlay.getOther().add(overlay);
        }
    }

}