package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import me.lizardofoz.inventorio.client.ui.HotbarHUDRenderer;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventorioMixin {

    @Mixin(HotbarHUDRenderer.class)
    public static class HotbarHUDRendererMixin {

        @Redirect(method = "renderSegmentedHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRenderSegmentedHotbar(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "renderHotbarAddons", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRenderHotbarAddons(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}