package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = InGameHud.class, priority = -999999999)
public abstract class InGameHudMixin {

    // HOTBAR SELECTOR
    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void drawHotbarSelector(float tickDelta, DrawContext context, CallbackInfo ci, PlayerEntity playerEntity) {
        context.drawTexture(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20, context.getScaledWindowHeight(), 0, 0, 24, 1);
    }

}