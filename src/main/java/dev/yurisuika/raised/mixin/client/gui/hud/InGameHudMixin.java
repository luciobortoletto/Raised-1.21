package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public static abstract class Pre {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void headStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            if (getSupport().pre) {
                matrices.translate(0, -getHud(), 0);
            }
        }

        // SPECTATOR MENU
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateSpectatorMenuStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateSpectatorMenuEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // HOTBAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateHotbarStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateHotbarEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // STATUS BARS
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateStatusBarsStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateStatusBarsEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // MOUNT HEALTH BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateMountHealthStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateMountHealthEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // MOUNT JUMP BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V"))
        private void translateMountJumpBarStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
        private void translateMountJumpBarEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // EXPERIENCE BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
        private void translateExperienceBarStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
        private void translateExperienceBarEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // HELD ITEM TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateHeldItemTooltipStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateHeldItemTooltipEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // SPECTATOR TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V"))
        private void translateSpectatorHudStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
        private void translateSpectatorHudEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // OVERLAY MESSAGE
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V"))
        private void translateOverlayMessageStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", shift = At.Shift.AFTER))
        private void translateOverlayMessageEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getHud(), 0);
        }

        // CHAT
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;III)V"))
        private void translateChatStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, -getChat(), +300);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;III)V", shift = At.Shift.AFTER))
        private void translateChatEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            matrices.translate(0, +getChat(), -300);
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void tailStart(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            if (getSupport().post) {
                matrices.translate(0, -getHud(), 0);
            }
        }

        // HOTBAR SELECTOR
        @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
        private int resizeHotbarSelector(int value) {
            return 24;
        }

        // HOTBAR ITEM
        @ModifyVariable(method = "renderHotbarItem", at = @At("HEAD"), index = 2, argsOnly = true)
        private int moveHotbarItem(int value) {
            return value - getHud();
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        //HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void headEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            if (getSupport().pre) {
                matrices.translate(0, +getHud(), 0);
            }
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void tailEnd(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
            if (getSupport().post) {
                matrices.translate(0, +getHud(), 0);
            }
        }

    }

}