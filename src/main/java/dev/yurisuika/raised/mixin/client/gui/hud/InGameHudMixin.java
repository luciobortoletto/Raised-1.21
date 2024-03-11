package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // RENDER (HEAD)
        @Inject(method = "render", at = @At("HEAD"))
        private void startRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                start(context, 0, getHud(), 0);
            }
        }

        // RENDER (TAIL)
        @Inject(method = "render", at = @At("TAIL"))
        private void startRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                start(context, 0, getHud(), 0);
            }
        }

        // SPECTATOR MENU
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // HOTBAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V"))
        private void startHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // STATUS BARS
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // MOUNT HEALTH BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // MOUNT JUMP BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void startMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void endMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // EXPERIENCE BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void startExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void endExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // HELD ITEM TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // SPECTATOR TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // OVERLAY MESSAGE
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0))
        private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
        private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // CHAT
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V"))
        private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getSync() ? getHud() : getChat(), 300);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V", shift = At.Shift.AFTER))
        private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // HOTBAR SELECTOR
        @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 1), index = 6)
        private int resizeHotbarSelector(int value) {
            return 24;
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        // RENDER (HEAD)
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context);
            }
        }

        // RENDER (TAIL)
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context);
            }
        }

    }

}