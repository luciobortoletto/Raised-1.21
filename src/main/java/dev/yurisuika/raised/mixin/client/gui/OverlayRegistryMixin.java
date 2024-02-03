package dev.yurisuika.raised.mixin.client.gui;

import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;

@Mixin(value = OverlayRegistry.class, remap = false)
public abstract class OverlayRegistryMixin {

    // MOD ALL BELOW
    @Inject(method = "registerOverlayBottom", at = @At("RETURN"))
    private static void addOverlayBottom(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        modAll.add(overlay);
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
    @Inject(method = "registerOverlayTop", at = @At("RETURN"))
    private static void addOverlayTop(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        modAll.add(overlay);
    }

}