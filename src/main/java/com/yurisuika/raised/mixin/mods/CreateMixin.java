package com.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.curiosities.armor.CopperBacktankArmorLayer;
import com.simibubi.create.content.curiosities.toolbox.ToolboxHandlerClient;
import com.simibubi.create.content.schematics.client.SchematicHotbarSlotOverlay;
import com.simibubi.create.foundation.gui.ToolSelectionScreen;
import com.yurisuika.raised.Raised;
import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class CreateMixin {

    @Mixin(CopperBacktankArmorLayer.class)
    public static class CopperBacktankArmorLayerMixin {

        @Redirect(method = "renderRemainingAirOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int redirectRenderRemainingAirOverlay(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(SchematicHotbarSlotOverlay.class)
    public static class SchematicHotbarSlotOverlayMixin {

        @Redirect(method = "renderOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectRenderOn(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(ToolboxHandlerClient.class)
    public static class ToolboxHandlerClientMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int redirectScaledHeight(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(ToolSelectionScreen.class)
    public static class ToolSelectionScreenMixin {

        @Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private int redirectDraw(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}