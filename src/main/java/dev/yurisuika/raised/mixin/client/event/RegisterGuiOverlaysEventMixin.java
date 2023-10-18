package dev.yurisuika.raised.mixin.client.event;

import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(RegisterGuiOverlaysEvent.class)
public class RegisterGuiOverlaysEventMixin {

    // PRE MOD
    @Inject(method = "registerBelow", at = @At("HEAD"))
    private void addOverlayBelow(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other) && getSupport()) {
            FMLLoader.getLoadingModList().getMods().forEach(mod -> {
                all.add(Identifier.tryParse(mod.getNamespace() + ":" + id));
                LoggerFactory.getLogger("raised").info("Registering pre mod element: " + mod.getNamespace() + ":" + id);
            });
        }
    }

    // POST MOD
    @Inject(method = "registerAbove", at = @At("HEAD"))
    private void addOverlayAbove(Identifier other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        if (hud.contains(other) && getSupport()) {
            FMLLoader.getLoadingModList().getMods().forEach(mod -> {
                all.add(Identifier.tryParse(mod.getNamespace() + ":" + id));
                LoggerFactory.getLogger("raised").info("Registering post mod element: " + mod.getNamespace() + ":" + id);
            });
        }
    }

}