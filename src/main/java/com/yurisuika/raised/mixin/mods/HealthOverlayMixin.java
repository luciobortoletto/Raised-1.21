package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.HealthRenderer;

@Mixin(HealthRenderer.class)
public class HealthOverlayMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
    private static int modifyScaledHeight(MainWindow instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

}