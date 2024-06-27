package dev.yurisuika.raised.client.gui.screen;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import dev.yurisuika.raised.client.gui.widget.IconToggleButtonWidget;
import dev.yurisuika.raised.client.option.RaisedKeyBindings;
import dev.yurisuika.raised.util.config.*;
import dev.yurisuika.raised.util.type.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.OrderableTooltip;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.*;

@Environment(EnvType.CLIENT)
public class RaisedScreen extends Screen {

    public ArrayList<IconToggleButtonWidget> elementsGrid = new ArrayList<>();
    public ArrayList<ClickableWidget> propertiesGrid = new ArrayList<>();
    public IconToggleButtonWidget hotbar;
    public IconToggleButtonWidget chat;
    public IconToggleButtonWidget bossbar;
    public IconToggleButtonWidget sidebar;
    public IconToggleButtonWidget effects;
    public IconToggleButtonWidget players;
    public IconToggleButtonWidget toasts;
    public IconToggleButtonWidget other;
    public ClickableWidget x;
    public ClickableWidget y;
    public ClickableWidget position;
    public ClickableWidget sync;
    public double time = 0.0F;
    public double duration = 25.0F;
    public double distance = 0.0F;
    public static Element element = Element.HOTBAR;

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        createElementsGrid();
        createPropertiesGrid();

        for (IconToggleButtonWidget widget : elementsGrid) {
            addDrawableChild(widget);
        }
        for (ClickableWidget widget : propertiesGrid) {
            addDrawableChild(widget);
        }
    }

    public void createElementsGrid() {
        hotbar = createIconToggleButton(Element.HOTBAR, 16, 16);
        chat = createIconToggleButton(Element.CHAT, 16 + 20 + 10, 16);
        bossbar = createIconToggleButton(Element.BOSSBAR, 16 + 20 + 10 + 20 + 10, 16);
        sidebar = createIconToggleButton(Element.SIDEBAR, 16 + 20 + 10 + 20 + 10 + 20 + 10, 16);
        effects = createIconToggleButton(Element.EFFECTS, 16, 16 + 20 + 5);
        players = createIconToggleButton(Element.PLAYERS, 16 + 20 + 10, 16 + 20 + 5);
        toasts = createIconToggleButton(Element.TOASTS, 16 + 20 + 10 + 20 + 10, 16 + 20 + 5);
        other = createIconToggleButton(Element.OTHER, 16 + 20 + 10 + 20 + 10 + 20 + 10, 16 + 20 + 5);

        setIconToggleButton(hotbar);
        setIconToggleButton(chat);
        setIconToggleButton(bossbar);
        setIconToggleButton(sidebar);
        setIconToggleButton(effects);
        setIconToggleButton(players);
        setIconToggleButton(toasts);
        setIconToggleButton(other);

        elementsGrid.add(hotbar);
        elementsGrid.add(chat);
        elementsGrid.add(bossbar);
        elementsGrid.add(sidebar);
        elementsGrid.add(effects);
        elementsGrid.add(players);
        elementsGrid.add(toasts);
        elementsGrid.add(other);
    }

    public void createPropertiesGrid() {
        x = new SimpleOption<>("options.raised.x", SimpleOption.constantTooltip(Text.translatable("options.raised.x.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(Math.round(Math.ceil((value.floatValue() / ((float)client.getWindow().getScaledWidth() / 4)) * 100)) + "%")), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledWidth() / 4), Option.getX(element), value -> Option.setX(element, value)).createButton(client.options, width - 110 - 16, 16, 110);
        y = new SimpleOption<>("options.raised.y", SimpleOption.constantTooltip(Text.translatable("options.raised.y.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(Math.round(Math.ceil((value.floatValue() / ((float)client.getWindow().getScaledHeight() / 4)) * 100)) + "%")), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), Option.getY(element), value -> Option.setY(element, value)).createButton(client.options, width - 110 - 16, 16 + 20 + 5, 110);
        position = new SimpleOption<>("options.raised.position", client -> value -> textRenderer.wrapLines(Text.translatable("options.raised.position.tooltip"), 200), SimpleOption.enumValueText(), new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Position.values()), Codec.INT.xmap(Position::byId, Position::getId)), Position.byName(Option.getPosition(element).asString()), value -> Option.setPosition(element, value)).createButton(client.options, width - 110 - 16, 16 + 20 + 5 + 20 + 5, 110);
        sync = new SimpleOption<>("options.raised.sync", client -> value -> textRenderer.wrapLines(Text.translatable("options.raised.sync." + value.asString() + ".tooltip", Text.translatable(element.getTranslationKey())), 200), SimpleOption.enumValueText(), new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Sync.values()), Codec.INT.xmap(Sync::byId, Sync::getId)), Sync.byName(Option.getSync(element).asString()), value -> Option.setSync(element, value)).createButton(client.options, width - 110 - 16, 16 + 20 + 5 + 20 + 5 + 20 + 5, 110);

        propertiesGrid.add(x);
        propertiesGrid.add(y);
        propertiesGrid.add(position);
        propertiesGrid.add(sync);
    }

    public IconToggleButtonWidget createIconToggleButton(Element element, int x, int y) {
        return new IconToggleButtonWidget(x, y, 20, 20, Text.translatable(element.getTranslationKey()), 20, 20, new Identifier("raised:textures/gui/icon/" + element.asString() + ".png"), button -> {
            RaisedScreen.element = element;
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }, element == RaisedScreen.element);
    }

    public void setIconToggleButton(IconToggleButtonWidget widget) {
        widget.active = !widget.toggled;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        super.render(matrixStack, mouseX, mouseY, delta);

        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, delta);

        drawCenteredText(matrixStack, textRenderer, Text.translatable("options.raised.title"), width / 2, 16 + 6, 16777215);
        drawCenteredText(matrixStack, textRenderer, Text.translatable(element.getTranslationKey()), width / 2, 16 + 6 + 20 + 5, 16777215);

        x.active = Option.getSync(element) == Sync.NONE;
        y.active = Option.getSync(element) == Sync.NONE;

        if (Option.getX(element) > client.getWindow().getScaledWidth() / 4) {
            Option.setX(element, client.getWindow().getScaledWidth() / 4);
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }
        if (Option.getY(element) > client.getWindow().getScaledHeight() / 4) {
            Option.setY(element, client.getWindow().getScaledHeight() / 4);
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }

        if (time < duration) {
            time += delta;
        }

        double animation = Math.min(time / duration, duration);

        if (animation < 1 / 2.75D) {
            distance = 7.5625D * animation * animation;
        } else if (animation < 2 / 2.75D) {
            distance = 7.5625D * (animation -= 1.5D / 2.75D) * animation + 0.75D;
        } else if (animation < 2.5D / 2.75D) {
            distance = 7.5625D * (animation -= 2.25D / 2.75D) * animation + 0.9375D;
        } else {
            distance = 7.5625D * (animation -= 2.625D / 2.75D) * animation + 0.984375D;
        }

        int x = Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element);
        int y = Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element);

        float percentX = (float)Math.round(Math.ceil(((float)x / ((float)client.getWindow().getScaledWidth() / 4)) * 100)) / 100;
        float percentY = (float)Math.round(Math.ceil(((float)y / ((float)client.getWindow().getScaledHeight() / 4)) * 100)) / 100;

        int offset = (int)((float)client.getWindow().getScaledHeight() / 2) - y;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int widthX = textRenderer.getWidth(stringX);
        int widthY = textRenderer.getWidth(stringY);
        Text translatableX = Text.translatable("options.raised.x");
        Text translatableY = Text.translatable("options.raised.y");

        matrixStack.push();
        matrixStack.scale(2, 2, 1);
        matrixStack.translate((distance * 91), 0, 300);
        RenderSystem.enableBlend();
        switch (element) {
            case HOTBAR -> {
                int slot = (int)MathHelper.lerp(percentX, 5, 8) * 20;

                RenderSystem.setShaderTexture(0, new Identifier("textures/gui/widgets.png"));
                drawTexture(matrixStack, -182, offset - 22, 0, 0, 182, 22);
                drawTexture(matrixStack, -182 - 1 + slot, offset - 23, 0, 22, 24, 24);

                RenderSystem.getModelViewStack().push();
                RenderSystem.getModelViewStack().scale(2, 2, 1);
                RenderSystem.getModelViewStack().translate((distance * 91), 0, 300);
                itemRenderer.renderInGuiWithOverrides(Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -182 + 123, offset - 19);
                itemRenderer.renderInGuiWithOverrides(Items.GLISTERING_MELON_SLICE.getDefaultStack(), -182 + 143, offset - 19);

                itemRenderer.renderGuiItemOverlay(textRenderer, Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -182 + 123, offset - 19, stringX);
                itemRenderer.renderGuiItemOverlay(textRenderer, Items.GLISTERING_MELON_SLICE.getDefaultStack(), -182 + 143, offset - 19, stringY);
                RenderSystem.getModelViewStack().pop();
                RenderSystem.applyModelViewMatrix();
            }
            case CHAT -> {
                int backgroundOpacity = (int)(255.0 * client.options.getTextBackgroundOpacity().getValue());
                int textOpacity = (int)(255.0F * client.options.getChatOpacity().getValue() * 0.8999999761581421 + 0.10000000149011612);

                fill(matrixStack, -91, offset - 9 - 9, -91 + 120 + 4 + 4, offset, backgroundOpacity << 24);

                textRenderer.drawWithShadow(matrixStack, "<" + translatableX.getString() + "> " + x, -91 + 4, offset - 8 - 9, 16777215 + (textOpacity << 24));
                textRenderer.drawWithShadow(matrixStack, "<" + translatableY.getString() + "> " + y, -91 + 4, offset - 8, 16777215 + (textOpacity << 24));
            }
            case BOSSBAR -> {
                int width = Math.max(textRenderer.getWidth(translatableX), textRenderer.getWidth(translatableY));

                RenderSystem.setShaderTexture(0, new Identifier("textures/gui/bars.png"));
                drawTexture(matrixStack,-182, offset - 5 - 19, 0, 20, 182, 5);
                if (x > 0) {
                    int progress = (int)MathHelper.lerp(percentX, 91, 182);
                    drawTexture(matrixStack, -182, offset - 5 - 19, progress, 5, 0, 25, progress, 5, 256, 256);
                }

                drawTexture(matrixStack,-182, offset - 5, 0, 60, 182, 5);
                if (y > 0) {
                    int progress = (int)MathHelper.lerp(percentY, 91, 182);
                    drawTexture(matrixStack, -182, offset - 5, progress, 5, 0, 65, progress, 5, 256, 256);
                }

                textRenderer.drawWithShadow(matrixStack, translatableX, -91 + 8 + (width / 2) - (textRenderer.getWidth(translatableX) / 2), offset - 5 - 9 - 19, 16777215);
                textRenderer.drawWithShadow(matrixStack, translatableY, -91 + 8 + (width / 2) - (textRenderer.getWidth(translatableY) / 2), offset - 5 - 9, 16777215);
            }
            case SIDEBAR -> {
                Text title = Text.translatable("options.raised.element.sidebar");
                int width = Math.max(2 + Math.max(textRenderer.getWidth(translatableX), textRenderer.getWidth(translatableY)) + 9 + Math.max(widthX, widthY), 2 + textRenderer.getWidth(title));

                fill(matrixStack, -91 + 1, offset - 1 - 9 - 10 - 10, -91 + 1 + width, offset - 1 - 10 - 10, client.options.getTextBackgroundColor(0.4F));
                fill(matrixStack, -91 + 1, offset - 1 - 10 - 10, -91 + 1 + width, offset - 1, client.options.getTextBackgroundColor(0.3F));

                textRenderer.draw(matrixStack, title, -91 + 1 + (width / 2) - (textRenderer.getWidth(title) / 2), offset - 1 - 8 - 10 - 10, -1);

                textRenderer.draw(matrixStack, translatableX, -91 + 1 + 2, offset - 1 - 9 - 10, -1);
                textRenderer.draw(matrixStack, translatableY, -91 + 1 + 2, offset - 1 - 9, -1);

                textRenderer.draw(matrixStack, String.valueOf(x), -91 + 1 + width - widthX, offset - 1 - 9 - 10, -2142128);
                textRenderer.draw(matrixStack, String.valueOf(y), -91 + 1 + width - widthY, offset - 1 - 9, -2142128);
            }
            case EFFECTS -> {
                RenderSystem.setShaderTexture(0, new Identifier("textures/gui/container/inventory.png"));
                drawTexture(matrixStack, -91 + 1, offset - 24 - 1, 141, 166, 24, 24);
                drawTexture(matrixStack, -91 + 1 + 24 + 1, offset - 24 - 1, 141, 166, 24, 24);

                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, percentX);
                RenderSystem.setShaderTexture(0, new Identifier("textures/mob_effect/luck.png"));
                drawTexture(matrixStack,-91 + 1 + 3, offset - 24 - 1 + 3, 0, 0, 18, 18, 18, 18);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, percentY);
                RenderSystem.setShaderTexture(0, new Identifier("textures/mob_effect/unluck.png"));
                drawTexture(matrixStack,-91 + 1 + 3 + 24 + 1, offset - 24 - 1 + 3, 0, 0, 18, 18, 18, 18);

                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            case PLAYERS -> {
                GameProfile profilePlayer = client.getSessionService().fillProfileProperties(client.player.getGameProfile(), false);
                GameProfile profileNotch = client.getSessionService().fillProfileProperties(new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch"), false);

                fill(matrixStack, -91 + 1, offset - 1 - 10 - 9, -91 + 1 + 144, offset - 1, Integer.MIN_VALUE);
                fill(matrixStack, -91 + 1 + 1, offset - 1 - 9 - 9, -91 + 1 + 144, offset - 1 - 1 - 9, client.options.getTextBackgroundColor(553648127));
                fill(matrixStack, -91 + 1 + 1, offset - 1 - 9, -91 + 1 + 144, offset - 1 - 1, client.options.getTextBackgroundColor(553648127));

                RenderSystem.setShaderTexture(0, client.getSkinProvider().loadSkin(profilePlayer));
                PlayerSkinDrawer.draw(matrixStack, -91 + 1 + 1, offset - 1 - 9 - 9, 8, client.player.isPartVisible(PlayerModelPart.HAT), "Dinnerbone".equals(profilePlayer.getName()) || "Grumm".equals(profilePlayer.getName()));
                RenderSystem.setShaderTexture(0, client.getSkinProvider().loadSkin(profileNotch));
                PlayerSkinDrawer.draw(matrixStack, -91 + 1 + 1, offset - 1 - 9, 8, true, false);

                textRenderer.drawWithShadow(matrixStack, translatableX, -91 + 1 + 10, offset - 1 - 9 - 9, -1);
                textRenderer.drawWithShadow(matrixStack, translatableY, -91 + 1 + 10, offset - 1 - 9, -1);

                textRenderer.drawWithShadow(matrixStack, stringX, -91 + 1 + 144 - 1 - 10 - 1 - widthX, offset - 1 - 9 - 9, -171);
                textRenderer.drawWithShadow(matrixStack, stringY, -91 + 1 + 144 - 1 - 10 - 1 - widthY, offset - 1 - 9, -171);

                RenderSystem.setShaderTexture(0, new Identifier("textures/gui/icons.png"));
                drawTexture(matrixStack, -91 + 1 + 144 - 1 - 10, offset - 1 - 9 - 9, 0, 176 + getSignal(percentX) * 8, 10, 8);
                drawTexture(matrixStack, -91 + 1 + 144 - 1 - 10, offset - 1 - 9, 0, 176 + getSignal(percentY) * 8, 10, 8);
            }
            case TOASTS -> {
                RenderSystem.setShaderTexture(0, new Identifier("textures/gui/toasts.png"));
                drawTexture(matrixStack, -91 - 40, offset - 32, 0, 0, 160, 32);

                RenderSystem.getModelViewStack().push();
                RenderSystem.getModelViewStack().scale(2, 2, 1);
                RenderSystem.getModelViewStack().translate((distance * 91), 0, 300);
                itemRenderer.renderInGui(Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -91 + 8, offset - 32 + 8);
                RenderSystem.getModelViewStack().pop();
                RenderSystem.applyModelViewMatrix();

                textRenderer.draw(matrixStack, translatableX.getString() + ": " + x, -91 + 30, offset - 32 + 7, 16776960 | -16777216);
                textRenderer.draw(matrixStack, translatableY.getString() + ": " + y, -91 + 30, offset - 32 + 18, -1);
            }
            case OTHER -> {
                textRenderer.drawWithShadow(matrixStack, translatableX.getString() + ": " + x, -91 + 8, offset - 8 - 8 - 4 - 8, -1);
                textRenderer.drawWithShadow(matrixStack, translatableY.getString() + ": " + y, -91 + 8, offset - 8 - 8, -1);
            }
        }
        RenderSystem.disableBlend();
        matrixStack.pop();

        for (IconToggleButtonWidget widget : elementsGrid) {
            if (widget != null && widget.isMouseOver(mouseX, mouseY)) {
                Element widgetElement = element;
                if (widget.equals(hotbar)) {
                    widgetElement = Element.HOTBAR;
                } else if (widget.equals(chat)) {
                    widgetElement = Element.CHAT;
                } else if (widget.equals(bossbar)) {
                    widgetElement = Element.BOSSBAR;
                } else if (widget.equals(sidebar)) {
                    widgetElement = Element.SIDEBAR;
                } else if (widget.equals(effects)) {
                    widgetElement = Element.EFFECTS;
                } else if (widget.equals(players)) {
                    widgetElement = Element.PLAYERS;
                } else if (widget.equals(toasts)) {
                    widgetElement = Element.TOASTS;
                } else if (widget.equals(other)) {
                    widgetElement = Element.OTHER;
                }
                renderOrderedTooltip(matrixStack, textRenderer.wrapLines(Text.translatable(widgetElement.getTranslationKey()), 200), mouseX, mouseY);
            }
        }
        for (ClickableWidget widget : propertiesGrid) {
            if (widget != null && widget.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrixStack, ((OrderableTooltip)widget).getOrderedTooltip(), mouseX, mouseY);
            }
        }
    }

    public static int getSignal(float percent) {
        int i;
        if (percent > 0.8F) {
            i = 0;
        } else if (percent > 0.6F) {
            i = 1;
        } else if (percent > 0.4F) {
            i = 2;
        } else if (percent > 0.2F) {
            i = 3;
        } else if (percent > 0.0F) {
            i = 4;
        } else {
            i = 5;
        }
        return i;
    }

    @Override
    public void renderBackground(MatrixStack matrixStack) {
        fillGradient(matrixStack, 0, 0, width, height, -1072689136, -804253680);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (RaisedKeyBindings.options.matchesKey(keyCode, scanCode)) {
            close();
            return true;
        }
        return true;
    }

}