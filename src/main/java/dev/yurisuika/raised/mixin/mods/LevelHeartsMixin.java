package dev.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class LevelHeartsMixin {

    @Mixin(IngameGui.class)
    public static class IngameGuiMixin {

        @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRedrawHealth(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRedrawAir(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRedrawArmor(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}