package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.mixin.client.gui.DrawContextInvoker;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Texture;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public abstract class InGameHudMixin {

    public abstract static class Hotbar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code spectator menu} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code hotbar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V"))
            private void startHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
             * for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code mount health bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code mount jump bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V"))
            private void startMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
            private void endMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code experience bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V"))
            private void startExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
            private void endExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code held item tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startSpectatorTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endSpectatorTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0))
            private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
            private void replaceHotbarSelector(Args args) {
                if (Option.getTexture() == Texture.REPLACE || (Option.getTexture() == Texture.AUTO && Pack.getPack())) {
                    args.set(0, new Identifier("raised:hud/hotbar_selection"));
                    args.set(4, 24);
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(float tickDelta, DrawContext context, CallbackInfo ci, PlayerEntity playerEntity) {
                if (Option.getTexture() == Texture.PATCH  || (Option.getTexture() == Texture.AUTO && !Pack.getPack())) {
                    int x = (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20;
                    int y = context.getScaledWindowHeight();
                    ((DrawContextInvoker)context).invokeDrawTexturedQuad(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
                }
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V"))
            private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V", shift = At.Shift.AFTER))
            private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
            private void startScoreboardTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Element.EFFECTS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startStatusEffectTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.EFFECTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Element.PLAYERS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
            private void startPlayerListTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.PLAYERS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/gui/DrawContext;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER))
            private void endPlayerListTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
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
            private void startRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.OTHER);
            }

        }

        @Mixin(value = InGameHud.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

}