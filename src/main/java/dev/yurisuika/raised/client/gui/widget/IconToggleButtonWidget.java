package dev.yurisuika.raised.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class IconToggleButtonWidget extends ButtonWidget {

    public final Identifier texture;
    public final int textureWidth;
    public final int textureHeight;
    public boolean toggled;
    public static final Identifier TEXTURE = new Identifier("textures/gui/widgets.png");

    public IconToggleButtonWidget(int x, int y, int width, int height, Text message, int textureWidth, int textureHeight, Identifier texture, PressAction onPress, boolean toggled) {
        super(x, y, width, height, message, onPress, (button, matrices, mouseX, mouseY) -> {});
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.toggled = toggled;
    }

    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        if (texture != null) {
            RenderSystem.disableDepthTest();
            int v;
            if (!isToggled()) {
                if (isHovered() || isFocused()) {
                    v = 86;
                } else {
                    v = 66;
                }
            }  else {
                v = 46;
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            drawTexture(matrixStack, x, y, 0, v, width / 2, height);
            drawTexture(matrixStack, x + (width / 2), y, 200 - (width / 2), v, width / 2, height);
            RenderSystem.setShaderTexture(0, texture);
            drawTexture(matrixStack, x, y, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
            RenderSystem.enableDepthTest();
        }
    }

}