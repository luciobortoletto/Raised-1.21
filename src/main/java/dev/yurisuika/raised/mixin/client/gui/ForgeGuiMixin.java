package dev.yurisuika.raised.mixin.client.gui;

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
    private void redirectRightHeight(ForgeGui instance, int value) {
        instance.rightHeight = value + Raised.getHud();
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;leftHeight:I", opcode = Opcodes.PUTFIELD))
    private void redirectLeftHeight(ForgeGui instance, int value) {
        instance.leftHeight = value + Raised.getHud();
    }

    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyChat(int value) {
        return value - Raised.getHud() - Raised.getChat();
    }

    @ModifyVariable(method = "renderRecordOverlay", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyActionbar(int value) {
        return value - Raised.getHud();
    }

}