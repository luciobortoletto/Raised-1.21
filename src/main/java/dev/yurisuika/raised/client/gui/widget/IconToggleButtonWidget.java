package dev.yurisuika.raised.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.tooltip.Tooltip;
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

    public IconToggleButtonWidget(int x, int y, int width, int height, Text message, int textureWidth, int textureHeight, Identifier texture, PressAction onPress, NarrationSupplier narrationSupplier, boolean toggled) {
        super(x, y, width, height, message, onPress, narrationSupplier == null ? DEFAULT_NARRATION_SUPPLIER : narrationSupplier);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.toggled = toggled;
    }

    public static Builder builder(Text text, PressAction onPress, boolean toggled) {
        return new Builder(text, onPress, toggled);
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
            drawTexture(matrixStack, getX(), getY(), 0, v, width / 2, height);
            drawTexture(matrixStack, getX() + (width / 2), getY(), 200 - (width / 2), v, width / 2, height);
            RenderSystem.setShaderTexture(0, texture);
            drawTexture(matrixStack, getX(), getY(), 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
            RenderSystem.enableDepthTest();
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Builder {

        public final Text text;
        public final PressAction onPress;
        public Tooltip tooltip;
        public int x;
        public int y;
        public int width = 150;
        public int height = 20;
        public Identifier texture;
        public int textureWidth;
        public int textureHeight;
        public NarrationSupplier narrationSupplier;
        public boolean toggled;

        public Builder(Text text, PressAction onPress, boolean toggled) {
            this.text = text;
            this.onPress = onPress;
            this.toggled = toggled;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder dimensions(int x, int y, int width, int height) {
            return position(x, y).size(width, height);
        }

        public Builder tooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Builder texture(Identifier texture, int width, int height) {
            this.texture = texture;
            this.textureWidth = width;
            this.textureHeight = height;
            return this;
        }

        public Builder narrationSupplier(NarrationSupplier narrationSupplier) {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        public IconToggleButtonWidget build() {
            if (texture == null) {
                throw new IllegalStateException("Sprite not set");
            } else {
                IconToggleButtonWidget iconToggleButtonWidget = new IconToggleButtonWidget(x, y, width, height, text, textureWidth, textureHeight, texture, onPress, narrationSupplier, toggled);
                iconToggleButtonWidget.setTooltip(tooltip);
                return iconToggleButtonWidget;
            }
        }

    }

}