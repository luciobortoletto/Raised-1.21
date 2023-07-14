package dev.yurisuika.raisedxcreate.mixin.mods;

import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;
import com.simibubi.create.content.trains.TrainHUD;
import com.simibubi.create.content.equipment.toolbox.ToolboxHandlerClient;
import com.simibubi.create.content.schematics.client.SchematicHotbarSlotOverlay;
import com.simibubi.create.content.schematics.client.ToolSelectionScreen;
import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class CreateMixin {

    @Pseudo
    @Mixin(value = RemainingAirOverlay.class, remap = false)
    public static class RemainingAirOverlayMixin {

        @ModifyVariable(method = "render", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private int modifyRender(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = SchematicHotbarSlotOverlay.class, remap = false)
    public static class SchematicHotbarSlotOverlayMixin {

        @ModifyVariable(method = "renderOn", at = @At("STORE"), ordinal = 2)
        private int modifyRenderOn(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = ToolboxHandlerClient.class, remap = false)
    public static class ToolboxHandlerClientMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = ToolSelectionScreen.class, remap = false)
    public static class ToolSelectionScreenMixin {

        @ModifyVariable(method = "draw", at = @At("STORE"), ordinal = 1)
        private int modifyDraw(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = TrainHUD.class, remap = false)
    public static class TrainHUDMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

}