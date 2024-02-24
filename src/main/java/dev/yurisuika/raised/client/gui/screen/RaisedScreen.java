package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.mixin.client.option.OptionInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.util.OrderableTooltip;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static dev.yurisuika.raised.client.option.RaisedKeyBinding.*;

@Environment(EnvType.CLIENT)
public abstract class RaisedScreen extends Screen {

    public ArrayList<ClickableWidget> grid = new ArrayList<>();
    public CheckboxWidget checkbox;
    public ClickableWidget support;
    public ClickableWidget sync;
    public ClickableWidget share;

    public RaisedScreen(Text title) {
        super(title);
    }

    public void setScreenType() {
        if (client.currentScreen instanceof SliderScreen) {
            client.setScreen(new TextScreen(new TranslatableText("options.raised.title")));
        } else if (client.currentScreen instanceof TextScreen) {
            client.setScreen(new SliderScreen(new TranslatableText("options.raised.title")));
        }
    }

    @Override
    public void init() {
        checkbox = new CheckboxWidget((width / 2) - 100, 32, 200, 20, new TranslatableText("options.raised.checkbox").styled(style -> style.withFormatting(Formatting.WHITE)), client.currentScreen instanceof TextScreen);

        support = CyclingOption.create("options.raised.support", new TranslatableText("options.raised.support.tooltip"), gameOptions -> getSupport(), (gameOptions, option, value) -> setSupport(value)).createButton(client.options, (width / 2) - 100, 104, 98);
        sync = CyclingOption.create("options.raised.sync", new TranslatableText("options.raised.sync.tooltip"), gameOptions -> getSync(), (gameOptions, option, value) -> setSync(value)).createButton(client.options, (width / 2) + 2, 104, 98);
        share = CyclingOption.create("options.raised.share", new TranslatableText("options.raised.share.tooltip"), gameOptions -> getShare(), (gameOptions, option, value) -> setShare(value)).createButton(client.options, (width / 2) - 100, 128, 200);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if ((checkbox.isChecked() && client.currentScreen instanceof SliderScreen) || (!checkbox.isChecked() && client.currentScreen instanceof TextScreen)) {
            setScreenType();
        }

        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        if (checkbox != null && checkbox.isMouseOver(mouseX, mouseY)) {
            renderOrderedTooltip(matrices, textRenderer.wrapLines(new TranslatableText("options.raised.checkbox.tooltip"), 200), mouseX, mouseY);
        }

        for (ClickableWidget option : grid) {
            if (option != null && !(option instanceof CheckboxWidget || option instanceof TextFieldWidget) && option.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, ((OrderableTooltip)option).getOrderedTooltip(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        fillGradient(matrices, (width / 2) - 108, 24, (width / 2) + 108, 24 + 132 , -1072689136, -804253680);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (options.matchesKey(keyCode, scanCode)) {
            close();
            return true;
        }
        return true;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public static class SliderScreen extends RaisedScreen {

        public ClickableWidget hud;
        public ClickableWidget chat;

        public SliderScreen(Text title) {
            super(title);
        }

        @Override
        public void init() {
            super.init();

            hud = new DoubleOption("options.raised.hud", 0, client.getWindow().getScaledHeight() / 4, 1.0F, gameOptions -> (double)getHud(), (gameOptions, value) -> setHud(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions)))), client -> client.textRenderer.wrapLines(new TranslatableText("options.raised.hud.tooltip"), 200)).createButton(client.options, (width / 2) - 100, 56, 200);
            chat = new DoubleOption("options.raised.chat", 0, client.getWindow().getScaledHeight() / 4, 1.0F, gameOptions -> (double)getChat(), (gameOptions, value) -> setChat(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions)))), client -> client.textRenderer.wrapLines(new TranslatableText("options.raised.chat.tooltip"), 200)).createButton(client.options, (width / 2) - 100, 80, 200);

            grid.add(checkbox);
            grid.add(hud);
            grid.add(chat);
            grid.add(support);
            grid.add(sync);
            grid.add(share);

            for (ClickableWidget option : grid) {
                addDrawableChild(option);
            }
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            int max = client.getWindow().getScaledHeight() / 4;
            boolean changed = false;

            if (getHud() > max) {
                setHud(max);
                changed = true;
            }
            if (getChat() > max) {
                setChat(max);
                changed = true;
            }
            if (changed) {
                client.setScreen(new SliderScreen(new TranslatableText("options.raised.title")));
            }

            super.render(matrices, mouseX, mouseY, delta);
        }

        @Override
        public void resize(MinecraftClient client, int width, int height) {
            client.setScreen(new SliderScreen(new TranslatableText("options.raised.title")));
        }

    }

    public static class TextScreen extends RaisedScreen {

        public TextFieldWidget hud;
        public TextFieldWidget chat;

        public TextScreen(Text title) {
            super(title);
        }

        @Override
        public void init() {
            super.init();

            hud = new TextFieldWidget(textRenderer, (width / 2) - 25, 56, 50, 20, new TranslatableText("options.raised.hud"));
            hud.setText(String.valueOf(getHud()));
            hud.setMaxLength(7);
            hud.setChangedListener(value -> {
                if (value.matches("[0-9]+") || value.isEmpty()) {
                    setHud(Integer.parseInt(value.isEmpty() ? "0" : value));
                }
            });
            chat = new TextFieldWidget(textRenderer, (width / 2) - 25, 80, 50, 20, new TranslatableText("options.raised.chat"));
            chat.setText(String.valueOf(getChat()));
            chat.setMaxLength(7);
            chat.setChangedListener(value -> {
                if (value.matches("[0-9]+") || value.isEmpty()) {
                    setChat(Integer.parseInt(value.isEmpty() ? "0" : value));
                }
            });

            grid.add(checkbox);
            grid.add(hud);
            grid.add(chat);
            grid.add(support);
            grid.add(sync);
            grid.add(share);

            for (ClickableWidget option : grid) {
                addDrawableChild(option);
            }
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);

            if (hud != null && hud.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, textRenderer.wrapLines(new TranslatableText("options.raised.hud.tooltip"), 200), mouseX, mouseY);
            }
            if (chat != null && chat.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, textRenderer.wrapLines(new TranslatableText("options.raised.chat.tooltip"), 200), mouseX, mouseY);
            }
        }

        @Override
        public void resize(MinecraftClient client, int width, int height) {
            client.setScreen(new TextScreen(new TranslatableText("options.raised.title")));
        }

    }

}