package com.yurisuika.raised.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import static com.yurisuika.raised.Raised.*;
import static net.minecraft.commands.Commands.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendSuccess(Component.translatable("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setHud(2);
                                    setChat(0);
                                    context.getSource().sendSuccess(Component.translatable("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("query")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendSuccess(Component.translatable("commands.raised.query.hud", config.hud), false);
                                    return 1;
                                })
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendSuccess(Component.translatable("commands.raised.query.chat", config.chat), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("set")
                        .then(literal("hud")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendSuccess(Component.translatable("commands.raised.set.hud", config.hud), false);
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendSuccess(Component.translatable("commands.raised.set.chat", config.chat), false);
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }
    
}