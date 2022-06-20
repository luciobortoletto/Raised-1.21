package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ForgeIngameGui.class, remap = false)
public class ForgeIngameGuiRemapFalseMixin {

    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyChat(int value) {
        return value - Raised.getDistance() - Raised.getOffset();
    }

    @ModifyVariable(method = "renderRecordOverlay", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyActionbar(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderFood", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyFood(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderHealth", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyHealth(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderAir", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyAir(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderHealthMount", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private int modifyHealthMount(int value) {
        return value - Raised.getDistance();
    }

}
