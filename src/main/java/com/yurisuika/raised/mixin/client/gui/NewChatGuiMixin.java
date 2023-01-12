package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraft.client.gui.NewChatGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(NewChatGui.class)
public class NewChatGuiMixin {

    @ModifyVariable(method = "handleChatQueueClicked", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double modifyMouseClick(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

    @ModifyVariable(method = "getClickedComponentStyleAt", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double modifyChatTooltip(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

}