package com.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class LevelHeartsMixin {

    @Mixin(IngameGui.class)
    public static class IngameGuiMixin {

        @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int modifyRedrawHealth(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

        @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int modifyRedrawAir(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

        @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int modifyRedrawArmor(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}