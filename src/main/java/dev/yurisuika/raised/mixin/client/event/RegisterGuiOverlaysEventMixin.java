package dev.yurisuika.raised.mixin.client.event;

import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fml.ModLoadingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;

@Mixin(value = RegisterGuiOverlaysEvent.class, remap = false)
public abstract class RegisterGuiOverlaysEventMixin {

    // MOD ALL BELOW
    @Inject(method = "registerBelowAll", at = @At("HEAD"))
    private void addOverlayBelowAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
        modAll.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
    }

    // MOD HUD BELOW
    @Inject(method = "registerBelow", at = @At("HEAD"))
    private void addOverlayBelow(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other)) {
            modHud.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
        }
    }

    // MOD HUD ABOVE
    @Inject(method = "registerAbove", at = @At("HEAD"))
    private void addOverlayAbove(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other)) {
            modHud.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
        }
    }

    // MOD ALL ABOVE
    @Inject(method = "registerAboveAll", at = @At("HEAD"))
    private void addOverlayAboveAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
        modAll.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
    }

}