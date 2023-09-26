package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.SpectatorHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = SpectatorHud.class, priority = -999999999)
public abstract class SpectatorHudMixin {

    // SPECTATOR MENU
    @Inject(method = "renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"))
    private void translateSpectatorMenuStart(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, -getHud(), 0);
    }
    @Inject(method = "renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("TAIL"))
    private void translateSpectatorMenuEnd(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, +getHud(), 0);
    }

    // SPECTATOR MENU TOOLTIP
    @Inject(method = "render", at = @At("HEAD"))
    private void heldItemTooltipStart(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, -getHud(), 0);
    }
    @Inject(method = "render", at = @At("TAIL"))
    private void heldItemTooltipEnd(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, +getHud(), 0);
    }

}