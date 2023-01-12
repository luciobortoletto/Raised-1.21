package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.anti_ad.mc.ipnext.event.LockSlotsHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventoryProfilesNextMixin {

    @Mixin(LockSlotsHandler.class)
    public static class LockSlotsHandlerMixin {

        @Redirect(method = "drawHotSprite", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectDrawHotSprite(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}