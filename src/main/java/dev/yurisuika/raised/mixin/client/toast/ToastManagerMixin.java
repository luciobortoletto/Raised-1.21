package dev.yurisuika.raised.mixin.client.toast;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class ToastManagerMixin {

    public abstract static class Toasts {

        @Mixin(targets = "net.minecraft.client.toast.ToastManager$Entry", priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code toasts} if {@link Element.TOASTS} is enabled.
             */
            @Inject(method = "draw", at = @At("HEAD"))
            private void startToastsTranslate(int x, MatrixStack matrixStack, CallbackInfoReturnable<Boolean> cir) {
                Translate.start(matrixStack, Element.TOASTS);
            }

            @Inject(method = "draw", at = @At("TAIL"))
            private void endToastsTranslate(int x, MatrixStack matriStack, CallbackInfoReturnable<Boolean> cir) {
                Translate.end(matriStack);
            }

        }

    }

}