package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.mixin.client.option.OptionInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Environment(EnvType.CLIENT)
public class RaisedScreen extends Screen {

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        addButton(new DoubleOption("options.raised.hud", 0, client.getWindow().getScaledHeight() / 2, 1.0F, gameOptions -> (double)getHud(), (gameOptions, value) -> setHud(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions))))).createButton(client.options, 16, 32, 200));
        addButton(new DoubleOption("options.raised.chat", 0, client.getWindow().getScaledHeight() / 2, 1.0F, gameOptions -> (double)getChat(), (gameOptions, value) -> setChat(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions))))).createButton(client.options, 16, 56, 200));
        addButton(new BooleanOption("options.raised.support", new TranslatableText("options.raised.support.tooltip"), gameOptions -> getSupport(), (gameOptions, value) -> setSupport(value)).createButton(client.options, 16, 80, 98));
        addButton(new BooleanOption("options.raised.sync", new TranslatableText("options.raised.sync.tooltip"), gameOptions -> getSync(), (gameOptions, value) -> setSync(value)).createButton(client.options, 118, 80, 98));
        addButton(new BooleanOption("options.raised.share", new TranslatableText("options.raised.share.tooltip"), gameOptions -> getShare(), (gameOptions, value) -> setShare(value)).createButton(client.options, 16, 104, 200));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        drawTextWithShadow(matrices, this.textRenderer, new TranslatableText("options.raised.title"), 16, 16, -1);
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}