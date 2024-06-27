package dev.yurisuika.raised.mixin.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class MinecraftClientMixin {

    public abstract static class Toasts {

        @Mixin(value = MinecraftClient.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code toasts} if {@link Element.TOASTS} is enabled.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startToastsTranslate(boolean tick, CallbackInfo ci) {
                Translate.start(RenderSystem.getModelViewStack(), Element.TOASTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endToastsTranslate(boolean tick, CallbackInfo ci) {
                Translate.end(RenderSystem.getModelViewStack());
            }

        }

    }

}