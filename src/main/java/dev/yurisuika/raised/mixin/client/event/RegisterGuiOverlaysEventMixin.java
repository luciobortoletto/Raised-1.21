package dev.yurisuika.raised.mixin.client.event;

import dev.yurisuika.raised.util.Overlay;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RegisterGuiOverlaysEvent.class, remap = false)
public abstract class RegisterGuiOverlaysEventMixin {

    /**
     * Adds mods registered below all overlays to be moved.
     */
    @Inject(method = "registerBelowAll(Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
    private void addOverlayBelowAll(ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
        Overlay.getOther().add(id);
    }

    /**
     * Adds mods registered below certain overlays all to be moved.
     */
    @Inject(method = "registerBelow(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
    private void addOverlayBelow(ResourceLocation other, ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
        if (Overlay.getHotbar().contains(other)) {
            Overlay.getHotbar().add(id);
        } else if (Overlay.getChat().contains(other)) {
            Overlay.getChat().add(id);
        } else if (Overlay.getBossbar().contains(other)) {
            Overlay.getBossbar().add(id);
        } else if (Overlay.getSidebar().contains(other)) {
            Overlay.getSidebar().add(id);
        } else if (Overlay.getEffects().contains(other)) {
            Overlay.getEffects().add(id);
        } else if (Overlay.getPlayers().contains(other)) {
            Overlay.getPlayers().add(id);
        } else if (Overlay.getToasts().contains(other)) {
            Overlay.getToasts().add(id);
        }
    }

    /**
     * Adds mods registered above certain overlays all to be moved.
     */
    @Inject(method = "registerAbove(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
    private void addOverlayAbove(ResourceLocation other, ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
        if (Overlay.getHotbar().contains(other)) {
            Overlay.getHotbar().add(id);
        } else if (Overlay.getChat().contains(other)) {
            Overlay.getChat().add(id);
        } else if (Overlay.getBossbar().contains(other)) {
            Overlay.getBossbar().add(id);
        } else if (Overlay.getSidebar().contains(other)) {
            Overlay.getSidebar().add(id);
        } else if (Overlay.getEffects().contains(other)) {
            Overlay.getEffects().add(id);
        } else if (Overlay.getPlayers().contains(other)) {
            Overlay.getPlayers().add(id);
        } else if (Overlay.getToasts().contains(other)) {
            Overlay.getToasts().add(id);
        }
    }

    /**
     * Adds mods registered above all overlays to be moved.
     */
    @Inject(method = "registerAboveAll(Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
    private void addOverlayAboveAll(ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
        Overlay.getOther().add(id);
    }

}