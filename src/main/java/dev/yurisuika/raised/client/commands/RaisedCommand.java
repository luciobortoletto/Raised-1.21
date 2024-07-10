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
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("reset")
                                .executes(commandContext -> {
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
                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(Commands.literal("raised")
                    .then(Commands.literal("elements")
                            .then(Commands.literal(element.getSerializedName())
                                    .then(Commands.literal("x")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.x.query", new TranslatableComponent(element.getKey()), Option.getY(element)), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(commandContext, "x"));
                                                        commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.x.set", new TranslatableComponent(element.getKey()), Option.getX(element)), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("y")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.y.query", new TranslatableComponent(element.getKey()), Option.getY(element)), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(commandContext, "y"));
                                                        commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.y.set", new TranslatableComponent(element.getKey()), Option.getY(element)), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("position")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.position.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getSerializedName())), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("position", PositionArgument.position())
                                                    .executes(commandContext -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(commandContext, "position"));
                                                        commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.position.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getSerializedName())), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("sync")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.sync.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getSerializedName())), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("sync", SyncArgument.sync())
                                                    .executes(commandContext -> {
                                                        Option.setSync(element, SyncArgument.getSync(commandContext, "sync"));
                                                        commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.elements.element.sync.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getSerializedName())), false);
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