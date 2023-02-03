package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ForgeIngameGui.class, priority = -1)
public class ForgeIngameGuiMixin {

    @Shadow
    public static int left_height = 39 + Raised.getHud();
    @Shadow
    public static int right_height = 39 + Raised.getHud();

    @Inject(method = "render", at = @At("TAIL"))
    public void injectHeight(CallbackInfo ci) {
        right_height = 39 + Raised.getHud();
        left_height = 39 + Raised.getHud();
    }

    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyChat(int value) {
        return value - Raised.getChat();
    }

    @ModifyVariable(method = "renderRecordOverlay", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyActionbar(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderFood", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyFood(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderHealth", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyHealth(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderAir", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyAir(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderHealthMount", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyHealthMount(int value) {
        return value - Raised.getHud();
    }

}