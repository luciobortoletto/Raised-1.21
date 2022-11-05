package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

public class AppleskinMixin {

    @Mixin(HUDOverlayHandler.class)
    public static class HUDOverlayHandlerMixin {

        @Redirect(method = "onPreRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectOnPreRender(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

        @Redirect(method = "onRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectOnRender(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}