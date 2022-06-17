package com.yurisuika.raised.mixin.mods;

import com.redlimerl.detailab.render.ArmorBarRenderer;
import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorBarRenderer.class)
public class DetailArmorBarMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
    private int modifyScaledHeight(MainWindow instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

}
