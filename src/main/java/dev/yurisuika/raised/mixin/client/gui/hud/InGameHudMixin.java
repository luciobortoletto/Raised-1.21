package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.mixin.client.gui.DrawContextInvoker;
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

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = InGameHud.class, priority = -999999999)
public abstract class InGameHudMixin {

    // HOTBAR SELECTOR
    @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    private void resizeHotbarSelector(Args args) {
        if (config.toggle.texture) {
            args.set(0, new Identifier("raised:hud/hotbar_selection"));
            args.set(4, 24);
        }
    }

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void drawHotbarSelector(float tickDelta, DrawContext context, CallbackInfo ci, PlayerEntity playerEntity) {
        if (!config.toggle.texture) {
            int x = (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20;
            int y = context.getScaledWindowHeight();
            ((DrawContextInvoker)context).invokeDrawTexturedQuad(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
        }
    }

}