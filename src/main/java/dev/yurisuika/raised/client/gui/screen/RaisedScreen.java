package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.client.option.RaisedConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.OrderableTooltip;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public class RaisedScreen extends Screen {

    ArrayList<ClickableWidget> grid = new ArrayList<>();

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        grid.add(new SimpleOption<>("options.raised.hud", SimpleOption.constantTooltip(Text.translatable("options.raised.hud.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 2), getHud(), RaisedConfig::setHud).createButton(client.options, 16, 32, 200));
        grid.add(new SimpleOption<>("options.raised.chat", SimpleOption.constantTooltip(Text.translatable("options.raised.chat.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 2), getChat(), RaisedConfig::setChat).createButton(client.options, 16, 56, 200));
        grid.add(SimpleOption.ofBoolean("options.raised.support", SimpleOption.constantTooltip(Text.translatable("options.raised.support.tooltip")), getSupport(), RaisedConfig::setSupport).createButton(client.options, 16, 80, 98));
        grid.add(SimpleOption.ofBoolean("options.raised.sync", SimpleOption.constantTooltip(Text.translatable("options.raised.sync.tooltip")), getSync(), RaisedConfig::setSync).createButton(client.options, 118, 80, 98));
        for (ClickableWidget option : grid) {
            addDrawableChild(option);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("options.raised.title"), 16, 16, -1);
        for (ClickableWidget option : grid) {
            if (option != null && option.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, ((OrderableTooltip)option).getOrderedTooltip(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}