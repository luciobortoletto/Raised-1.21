package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.HealthRenderer;

public class HealthOverlayMixin {

    @Pseudo
    @Mixin(HealthRenderer.class)
    public static class HealthRendererMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private static int redirectRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}