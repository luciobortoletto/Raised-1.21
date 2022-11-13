package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraft.client.gui.SpectatorGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = SpectatorGui.class, priority = -1)
public class SpectatorGuiMixin {

    @ModifyArg(method = "renderPage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/SpectatorGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIII)V"), index = 2)
    private int modifySpectatorMenu(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderPage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/SpectatorGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
    private int modifySpectatorMenuSelector(int value) {
        return value + 2;
    }

    @ModifyArg(method = "renderPage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/SpectatorGui;renderSlot(Lcom/mojang/blaze3d/matrix/MatrixStack;IIFFLnet/minecraft/client/gui/spectator/ISpectatorMenuObject;)V"), index = 3)
    private float modifySpectatorCommand(float value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderTooltip", at = @At(value = "STORE"), ordinal = 2)
    private int modifyText(int value) {
        return value - Raised.getDistance();
    }

}
