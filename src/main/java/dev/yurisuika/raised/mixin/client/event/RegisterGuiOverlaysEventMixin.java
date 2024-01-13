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

@Mixin(RegisterGuiOverlaysEvent.class)
public abstract  class RegisterGuiOverlaysEventMixin {

    // MOD BELOW
    @Inject(method = "registerBelow", at = @At("HEAD"))
    private void addOverlayBelow(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other)) {
            mod.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
        }
    }

    // MOD ABOVE
    @Inject(method = "registerAbove", at = @At("HEAD"))
    private void addOverlayAbove(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other)) {
            mod.add(Identifier.tryParse(ModLoadingContext.get().getActiveNamespace() + ":" + id));
        }
    }

}