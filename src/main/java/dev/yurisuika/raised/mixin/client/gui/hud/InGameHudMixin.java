package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public abstract class InGameHudMixin {

    public abstract static class Hotbar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code spectator menu} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startSpectatorMenuTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code hotbar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startHotbarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endHotbarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
             * for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startStatusBarsTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endStatusBarsTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code mount health bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startMountHealthTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endMountHealthTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code mount jump bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V"))
            private void startMountJumpBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
            private void endMountJumpBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code experience bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V"))
            private void startExperienceBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V", shift = At.Shift.AFTER))
            private void endExperienceBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code held item tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startHeldItemTooltipTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startSpectatorHudTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endSpectatorHudTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0))
            private void startOverlayMessageTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            /**
             * Resizes the hotbar selector to draw the entire texture.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1))
            private void resizeHotbarSelector(Args args) {
                args.set(6, 24);
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;III)V"))
            private void startChatTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/util/math/MatrixStack;III)V", shift = At.Shift.AFTER))
            private void endChatTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

    public abstract static class Boss {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/BossBarHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startBossBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.BOSSBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/BossBarHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endBossBarTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
            private void startScoreboardTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Element.EFFECTS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/util/math/MatrixStack;)V"))
            private void startStatusEffectTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.EFFECTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Element.PLAYERS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
            private void startPlayerListTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.PLAYERS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER))
            private void endPlayerListTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

    public abstract static class Other {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startRenderHeadTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.start(matrixStack, Element.OTHER);
            }

        }

        @Mixin(value = InGameHud.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
                Translate.end(matrixStack);
            }

        }

    }

}