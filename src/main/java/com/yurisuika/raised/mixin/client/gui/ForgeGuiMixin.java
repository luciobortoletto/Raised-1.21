package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeGui.class, priority = -1)
public class ForgeGuiMixin {

    @Shadow
    public int leftHeight = 39 + Raised.getHud();
    @Shadow
    public int rightHeight = 39 + Raised.getHud();

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;rightHeight:I", opcode = Opcodes.PUTFIELD))
    private void redirectRight(ForgeGui instance, int value) {
        instance.rightHeight = value + Raised.getHud();
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;leftHeight:I", opcode = Opcodes.PUTFIELD))
    private void redirectLeft(ForgeGui instance, int value) {
        instance.leftHeight = value + Raised.getHud();
    }

}