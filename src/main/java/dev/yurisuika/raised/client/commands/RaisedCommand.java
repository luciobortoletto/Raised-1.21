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
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    commandContext.getSource().sendSuccess(Component.translatable("commands.raised.config.reload"), false);
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
                                    commandContext.getSource().sendSuccess(Component.translatable("commands.raised.config.reset"), false);
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
                                                commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.x.query", Component.translatable(element.getKey()), Option.getY(element)), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(commandContext, "x"));
                                                        commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.x.set", Component.translatable(element.getKey()), Option.getX(element)), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("y")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.y.query", Component.translatable(element.getKey()), Option.getY(element)), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(commandContext, "y"));
                                                        commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.y.set", Component.translatable(element.getKey()), Option.getY(element)), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("position")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.position.query", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("position", PositionArgument.position())
                                                    .executes(commandContext -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(commandContext, "position"));
                                                        commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.position.set", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())), false);
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(Commands.literal("sync")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.sync.query", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())), false);
                                                return 1;
                                            })
                                            .then(Commands.argument("sync", SyncArgument.sync())
                                                    .executes(commandContext -> {
                                                        Option.setSync(element, SyncArgument.getSync(commandContext, "sync"));
                                                        commandContext.getSource().sendSuccess(Component.translatable("commands.raised.elements.element.sync.set", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())), false);
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