package com.yurisuika.raised.mixin.mods;

import com.redlimerl.detailab.render.ArmorBarRenderer;
import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class DetailArmorBarMixin {

    @Mixin(ArmorBarRenderer.class)
    public static class ArmorBarRendererMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int modifyRender(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}
