package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = InGameHud.class, priority = -999999999)
public abstract class InGameHudMixin {

    // HELD ITEM TOOLTIP
    @Inject(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderSelectedItemName(Lnet/minecraft/client/gui/DrawContext;I)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void heldItemTooltipStart(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, -getHud(), 0);
    }
    @Inject(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderSelectedItemName(Lnet/minecraft/client/gui/DrawContext;I)V", ordinal = 0, shift = At.Shift.AFTER))
    private void heldItemTooltipEnd(DrawContext context, CallbackInfo ci) {
        context.getMatrices().translate(0, +getHud(), 0);
    }

    // HOTBAR SELECTOR
    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 1), index = 6)
    private int resizeHotbarSelector(int value) {
        return 24;
    }

}