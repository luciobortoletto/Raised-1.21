package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraft.client.gui.IngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = IngameGui.class, priority = -1)
public class IngameGuiMixin {

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/IngameGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIII)V"), index = 2)
    private int hotbarDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/IngameGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
    private int selectorHeight(int value) {
        return value + 2;
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/IngameGui;renderSlot(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V"), index = 1)
    private int itemDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderJumpMeter", at = @At(value = "STORE"), ordinal = 3)
    private int jumpBarDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/IngameGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIII)V"), index = 2)
    private int experienceBarDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;draw(Lcom/mojang/blaze3d/matrix/MatrixStack;Ljava/lang/String;FFI)I"), index = 3)
    private float xpTextDistance(float value) {
        return value - (float)Raised.getDistance();
    }

    @ModifyVariable(method = "renderSelectedItemName", at = @At(value = "STORE"), ordinal = 2)
    private int heldItemTooltipDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderPlayerHealth", at = @At(value = "STORE"), ordinal = 5)
    private int statusBarsDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderVehicleHealth", at = @At(value = "STORE"), ordinal = 2)
    private int mountHealthDistance(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;translatef(FFF)V", ordinal = 0), index = 1)
    private float modifyActionbar(float value) {
        return value - (float)Raised.getDistance();
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;translatef(FFF)V", ordinal = 2), index = 1)
    private float modifyChat(float value) {
        return value - (float)Raised.getDistance() - (float)Raised.getOffset();
    }

}
