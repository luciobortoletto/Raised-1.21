package dev.yurisuika.raised.client.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.command.argument.*;
import dev.yurisuika.raised.util.config.*;
import dev.yurisuika.raised.util.config.option.*;
import dev.yurisuika.raised.util.type.*;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.TranslatableText;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    Config.loadConfig();
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    Option.setElements(new Elements(
                                            new Properties.Hotbar(
                                                    0,
                                                    2,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            ),
                                            new Properties.Chat(
                                                    0,
                                                    0,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            ),
                                            new Properties.Bossbar(
                                                    0,
                                                    0,
                                                    Position.TOP,
                                                    Sync.NONE
                                            ),
                                            new Properties.Sidebar(
                                                    0,
                                                    0,
                                                    Position.RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Effects(
                                                    0,
                                                    0,
                                                    Position.TOP_RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Players(
                                                    0,
                                                    0,
                                                    Position.TOP,
                                                    Sync.NONE
                                            ),
                                            new Properties.Toasts(
                                                    0,
                                                    0,
                                                    Position.TOP_RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Other(
                                                    0,
                                                    0,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            )
                                    ));
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(literal("raised")
                    .then(literal("elements")
                            .then(literal(element.asString())
                                    .then(literal("x")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.x.query", new TranslatableText(element.getTranslationKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(argument("x", IntegerArgumentType.integer(0))
                                                    .executes(context -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(context, "x"));
                                                        context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.x.set", new TranslatableText(element.getTranslationKey()), Option.getX(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(literal("y")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.y.query", new TranslatableText(element.getTranslationKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(argument("y", IntegerArgumentType.integer(0))
                                                    .executes(context -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(context, "x"));
                                                        context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.y.set", new TranslatableText(element.getTranslationKey()), Option.getY(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(literal("position")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.position.query", new TranslatableText(element.getTranslationKey()), new TranslatableText(Option.getPosition(element).getTranslationKey())));
                                                return 1;
                                            })
                                            .then(argument("position", PositionArgumentType.position())
                                                    .executes(context -> {
                                                        Option.setPosition(element, PositionArgumentType.getPosition(context, "position"));
                                                        context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.position.set", new TranslatableText(element.getTranslationKey()), new TranslatableText(Option.getPosition(element).getTranslationKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(literal("sync")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.sync.query", new TranslatableText(element.getTranslationKey()), new TranslatableText(Option.getSync(element).getTranslationKey())));
                                                return 1;
                                            })
                                            .then(argument("sync", SyncArgumentType.sync())
                                                    .executes(context -> {
                                                        Option.setSync(element, SyncArgumentType.getSync(context, "sync"));
                                                        context.getSource().sendFeedback(new TranslatableText("commands.raised.elements.element.sync.set", new TranslatableText(element.getTranslationKey()), new TranslatableText(Option.getSync(element).getTranslationKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                            )
                    )
            );
        }
    }

}