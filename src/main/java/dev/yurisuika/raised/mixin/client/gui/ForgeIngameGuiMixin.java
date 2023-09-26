package dev.yurisuika.raised.mixin.client.gui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = ForgeIngameGui.class, priority = -999999999)
public abstract class ForgeIngameGuiMixin {

    // SPECTATOR MENU
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;F)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void translateSpectatorMenuStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, -getHud(), 0);
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;F)V", ordinal = 0, shift = At.Shift.AFTER))
    private void translateSpectatorMenuEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, +getHud(), 0);
    }

    // HELD ITEM TOOLTIP
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void heldItemTooltipStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, -getHud(), 0);
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void heldItemTooltipEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, +getHud(), 0);
    }

    // SPECTATOR TOOLTIP
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void translateSpectatorHudStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, -getHud(), 0);
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void translateSpectatorHudEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, +getHud(), 0);
    }

    // OVERLAY MESSAGE
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void overlayMessageStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, -getHud(), 0);
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void overlayMessageEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        matrices.translate(0, +getHud(), 0);
    }

}