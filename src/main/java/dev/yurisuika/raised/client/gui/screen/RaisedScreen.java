package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.client.option.RaisedConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.OrderableTooltip;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
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
            client.setScreen(new TextScreen(Text.translatable("options.raised.title")));
        } else if (client.currentScreen instanceof TextScreen) {
            client.setScreen(new SliderScreen(Text.translatable("options.raised.title")));
        }
    }

    @Override
    public void init() {
        checkbox = new CheckboxWidget((width / 2) - 100, 32, 200, 20, Text.translatable("options.raised.checkbox").styled(style -> style.withFormatting(Formatting.WHITE)), client.currentScreen instanceof TextScreen);
        support = SimpleOption.ofBoolean("options.raised.support", SimpleOption.constantTooltip(Text.translatable("options.raised.support.tooltip")), getSupport(), RaisedConfig::setSupport).createButton(client.options, (width / 2) - 100, 104, 98);
        sync = SimpleOption.ofBoolean("options.raised.sync", SimpleOption.constantTooltip(Text.translatable("options.raised.sync.tooltip")), getSync(), RaisedConfig::setSync).createButton(client.options, (width / 2) + 2, 104, 98);
        share = SimpleOption.ofBoolean("options.raised.share", SimpleOption.constantTooltip(Text.translatable("options.raised.share.tooltip")), getShare(), RaisedConfig::setShare).createButton(client.options, (width / 2) - 100, 128, 200);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if ((checkbox.isChecked() && client.currentScreen instanceof SliderScreen) || (!checkbox.isChecked() && client.currentScreen instanceof TextScreen)) {
            setScreenType();
        }

        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        if (checkbox != null && checkbox.isMouseOver(mouseX, mouseY)) {
            renderOrderedTooltip(matrices, textRenderer.wrapLines(Text.translatable("options.raised.checkbox.tooltip"), 200), mouseX, mouseY);
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

            hud = new SimpleOption<>("options.raised.hud", SimpleOption.constantTooltip(Text.translatable("options.raised.hud.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getHud(), RaisedConfig::setHud).createButton(client.options, (width / 2) - 100, 56, 200);
            chat = new SimpleOption<>("options.raised.chat", SimpleOption.constantTooltip(Text.translatable("options.raised.chat.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getChat(), RaisedConfig::setChat).createButton(client.options, (width / 2) - 100, 80, 200);

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
                client.setScreen(new SliderScreen(Text.translatable("options.raised.title")));
            }

            super.render(matrices, mouseX, mouseY, delta);
        }

        @Override
        public void resize(MinecraftClient client, int width, int height) {
            client.setScreen(new SliderScreen(Text.translatable("options.raised.title")));
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

            hud = new TextFieldWidget(textRenderer, (width / 2) - 25, 56, 50, 20, Text.translatable("options.raised.hud"));
            hud.setText(String.valueOf(getHud()));
            hud.setMaxLength(7);
            hud.setChangedListener(value -> {
                if (value.matches("[0-9]+") || value.isEmpty()) {
                    setHud(Integer.parseInt(value.isEmpty() ? "0" : value));
                }
            });
            chat = new TextFieldWidget(textRenderer, (width / 2) - 25, 80, 50, 20, Text.translatable("options.raised.chat"));
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
                renderOrderedTooltip(matrices, textRenderer.wrapLines(Text.translatable("options.raised.hud.tooltip"), 200), mouseX, mouseY);
            }
            if (chat != null && chat.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, textRenderer.wrapLines(Text.translatable("options.raised.chat.tooltip"), 200), mouseX, mouseY);
            }
        }

        @Override
        public void resize(MinecraftClient client, int width, int height) {
            client.setScreen(new TextScreen(Text.translatable("options.raised.title")));
        }

     }

}