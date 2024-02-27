package dev.yurisuika.raised.mixin.client.gui;

import com.google.common.collect.Lists;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;

@Mixin(value = OverlayRegistry.class, remap = false)
public abstract class OverlayRegistryMixin {

    @Unique
    private static List<String> vanilla = Lists.newArrayList(
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

    // MOD ALL BELOW
    @Inject(method = "registerOverlayBottom", at = @At("HEAD"))
    private static void addOverlayBottom(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (!vanilla.contains(displayName)) {
            modAll.add(overlay);
        }
    }

    // MOD HUD BELOW
    @Inject(method = "registerOverlayBelow", at = @At("RETURN"))
    private static void addOverlayBelow(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other)) {
            modHud.add(overlay);
        }
    }

    // MOD HUD ABOVE
    @Inject(method = "registerOverlayAbove", at = @At("RETURN"))
    private static void addOverlayAbove(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other)) {
            modHud.add(overlay);
        }
    }

    // MOD ALL ABOVE
    @Inject(method = "registerOverlayTop", at = @At("HEAD"))
    private static void addOverlayTop(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (!vanilla.contains(displayName)) {
            modAll.add(overlay);
        }
    }

}