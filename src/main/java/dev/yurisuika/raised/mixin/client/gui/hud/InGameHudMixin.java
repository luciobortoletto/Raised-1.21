package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // MAIN HUD
        @Inject(method = "renderMainHud", at = @At(value = "HEAD"))
        private void startSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }

        // SPECTATOR TOOLTIP
//        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
//        private void startSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
//            context.getMatrices().translate(0, -getHud(), 0);
//        }
//
//        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
//        private void endSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
//            context.getMatrices().translate(0, +getHud(), 0);
//        }

        // OVERLAY MESSAGE
        @Inject(method = "renderOverlayMessage", at = @At(value = "HEAD"))
        private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }

        // CHAT
        @Inject(method = "renderChat", at = @At(value = "HEAD"))
        private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -(getSync() ? getHud() : getChat()), +300);
        }

        // HOTBAR SELECTOR
        @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
        private void resizeHotbarSelector(Args args) {
            args.set(0, new Identifier("raised:hud/hotbar_selection"));
            args.set(4, 24);
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        // MAIN HUD
        @Inject(method = "renderMainHud", at = @At(value = "TAIL"))
        private void endSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // OVERLAY MESSAGE
        @Inject(method = "renderOverlayMessage", at = @At(value = "TAIL"))
        private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // CHAT
        @Inject(method = "renderChat", at = @At(value = "TAIL"))
        private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +(getSync() ? getHud() : getChat()), -300);
        }

    }

}