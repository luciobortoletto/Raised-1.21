package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.client.option.RaisedConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Environment(EnvType.CLIENT)
public class RaisedScreen extends Screen {

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(0, 0, 4, 4);
        GridWidget.Adder adder = gridWidget.createAdder(2);
        adder.add(new SimpleOption<>("options.raised.hud", SimpleOption.constantTooltip(Text.translatable("options.raised.hud.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getHud(), RaisedConfig::setHud).createButton(client.options, 0, 0, 200), 2);
        adder.add(new SimpleOption<>("options.raised.chat", SimpleOption.constantTooltip(Text.translatable("options.raised.chat.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getChat(), RaisedConfig::setChat).createButton(client.options, 0, 0, 200), 2);
        adder.add(SimpleOption.ofBoolean("options.raised.support", SimpleOption.constantTooltip(Text.translatable("options.raised.support.tooltip")), getSupport(), RaisedConfig::setSupport).createButton(client.options, 0, 0, 98));
        adder.add(SimpleOption.ofBoolean("options.raised.sync", SimpleOption.constantTooltip(Text.translatable("options.raised.sync.tooltip")), getSync(), RaisedConfig::setSync).createButton(client.options, 0, 0, 98));
        adder.add(SimpleOption.ofBoolean("options.raised.share", SimpleOption.constantTooltip(Text.translatable("options.raised.share.tooltip")), getShare(), RaisedConfig::setShare).createButton(client.options, 0, 0, 200), 2);
        gridWidget.recalculateDimensions();
        SimplePositioningWidget.setPos(gridWidget, 16, 32, this.width, this.height, 0.0f, 0.0f);
        addDrawableChild(gridWidget);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("options.raised.title"), 16, 16, -1);
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}