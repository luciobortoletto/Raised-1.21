package com.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IngameGui.class)
public class LevelHeartsMixin {

    @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
    private int modifyHeartsScaledHeight(MainWindow instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

    @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
    private int modifyAirScaledHeight(MainWindow instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

    @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
    private int modifyArmorScaledHeight(MainWindow instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

}
