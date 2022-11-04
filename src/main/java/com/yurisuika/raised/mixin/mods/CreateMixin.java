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

    @Mixin(value = CopperBacktankArmorLayer.class, remap = false)
    public static class CopperBacktankArmorMixin {

        @Redirect(method = "renderRemainingAirOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int modifyRenderRemainingAirOverlay(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

    @Mixin(value = SchematicHotbarSlotOverlay.class, remap = false)
    public static class SchematicHotbarSlotOverlayMixin {

        @Redirect(method = "renderOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int modifyRenderOn(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

    @Mixin(ToolboxHandlerClient.class)
    public static class ToolboxHandlerClientMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int modifyScaledHeight(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

    @Mixin(value = ToolSelectionScreen.class, remap = false)
    public static class ToolSelectionScreeMixin {

        @Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MainWindow;getGuiScaledHeight()I"))
        private static int modifyDraw(MainWindow instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}