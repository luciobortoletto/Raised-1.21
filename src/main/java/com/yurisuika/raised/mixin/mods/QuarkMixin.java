package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.quark.content.client.module.UsageTickerModule;
import vazkii.quark.content.management.module.HotbarChangerModule;

public class QuarkMixin {

    @Mixin(HotbarChangerModule.class)
    public static class HotbarChangerModuleMixin {

        @Redirect(method = "hudPost", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectHudPost(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(UsageTickerModule.TickerElement.class)
    public static class UsageTickerModuleMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRender(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}