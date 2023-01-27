package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;

public class BotaniaMixin {

    @Mixin(HUDHandler.class)
    public static class HUDHandlerMixin {

        @Redirect(method = "renderManaInvBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int redirectRenderManaInvBar(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(ItemFlightTiara.class)
    public static class ItemFlightTiaraMixin {

        @Redirect(method = "renderHUD", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int redirectRenderHUD(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}