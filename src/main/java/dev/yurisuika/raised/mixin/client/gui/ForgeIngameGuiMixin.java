package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeIngameGui.class)
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

}