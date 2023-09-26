package dev.yurisuika.raised.mixin.client.gui.overlay;

import net.minecraft.client.gui.DrawContext;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = ForgeGui.class, priority = -1)
public abstract class ForgeGuiMixin {

    // OVERLAY MESSAGE
    @Inject(method = "renderRecordOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0, shift = At.Shift.BEFORE))
    private void overlayMessageStart(int width, int height, float partialTick, DrawContext guiGraphics, CallbackInfo ci) {
        guiGraphics.getMatrices().translate(0, -getHud(), 0);
    }
    @Inject(method = "renderRecordOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
    private void overlayMessageEnd(int width, int height, float partialTick, DrawContext guiGraphics, CallbackInfo ci) {
        guiGraphics.getMatrices().translate(0, +getHud(), 0);
    }

}