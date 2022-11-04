package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.HealthRenderer;

public class HealthOverlayMixin {

    @Mixin(HealthRenderer.class)
    public static class HeartRendererMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int modifyRenderPlayerHearts(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}