package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.PositionArgument;
import dev.yurisuika.raised.commands.arguments.SyncArgument;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.option.Elements;
import dev.yurisuika.raised.util.config.option.Properties;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Position;
import dev.yurisuika.raised.util.type.Sync;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.network.chat.TranslatableComponent;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("raised")
                .then(ClientCommandManager.literal("config")
                        .then(ClientCommandManager.literal("reload")
                                .executes(context -> {
                                    Config.loadConfig();
                                    context.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
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
                                    context.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(ClientCommandManager.literal("raised")
                    .then(ClientCommandManager.literal("elements")
                            .then(ClientCommandManager.literal(element.getSerializedName())
                                    .then(ClientCommandManager.literal("x")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.x.query", new TranslatableComponent(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(context -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(context, "x"));
                                                        context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.x.set", new TranslatableComponent(element.getKey()), Option.getX(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("y")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.y.query", new TranslatableComponent(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(context -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(context, "y"));
                                                        context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.y.set", new TranslatableComponent(element.getKey()), Option.getY(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("position")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.position.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getSerializedName())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("position", PositionArgument.position())
                                                    .executes(context -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(context, "position"));
                                                        context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.position.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getSerializedName())));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("sync")
                                            .executes(context -> {
                                                context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.sync.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getSerializedName())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("sync", SyncArgument.sync())
                                                    .executes(context -> {
                                                        Option.setSync(element, SyncArgument.getSync(context, "sync"));
                                                        context.getSource().sendFeedback(new TranslatableComponent("commands.raised.elements.element.sync.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getSerializedName())));
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