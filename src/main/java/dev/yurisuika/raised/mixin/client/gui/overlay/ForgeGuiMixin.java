package dev.yurisuika.raised.mixin.client.gui.overlay;

import dev.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeGui.class, priority = -1)
public class ForgeGuiMixin {

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;rightHeight:I", opcode = Opcodes.PUTFIELD))
    private void redirectRight(ForgeGui instance, int value) {
        instance.rightHeight = value + Raised.getHud();
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;leftHeight:I", opcode = Opcodes.PUTFIELD))
    private void redirectLeft(ForgeGui instance, int value) {
        instance.leftHeight = value + Raised.getHud();
    }

    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyChat(int value) {
        return value - Raised.getChat();
    }

    @Redirect(method = "renderRecordOverlay", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"))
    private int modifyActionbarShift(int a, int b) {
        return a + 9;
    }

}