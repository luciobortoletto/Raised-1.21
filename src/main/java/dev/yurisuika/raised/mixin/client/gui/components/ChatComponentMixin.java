package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ChatComponent.class, priority = -1)
public class ChatComponentMixin {

    @ModifyVariable(method = "handleChatQueueClicked", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double modifyMouseClick(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

    @ModifyVariable(method = "screenToChatY", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private double modifyChatTooltip(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

    @ModifyVariable(method = "render", at = @At(value = "STORE"), ordinal = 6)
    private int modifyChat(int value) {
        return value - Raised.getHud() - Raised.getChat();
    }

}