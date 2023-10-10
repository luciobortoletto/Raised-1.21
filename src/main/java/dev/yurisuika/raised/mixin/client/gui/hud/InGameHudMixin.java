package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = InGameHud.class, priority = -999999999)
public abstract class InGameHudMixin {

    // HOTBAR SELECTOR
    @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    private void resizeHotbarSelector(Args args) {
        args.set(0, new Identifier("raised:hud/hotbar_selection"));
        args.set(4, 24);
    }

}