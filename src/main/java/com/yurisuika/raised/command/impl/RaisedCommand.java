package com.yurisuika.raised.command.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.TranslationTextComponent;

import static com.yurisuika.raised.Raised.*;
import static net.minecraft.command.Commands.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setHud(2);
                                    setChat(0);
                                    context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("query")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.query.hud", config.hud), false);
                                    return 1;
                                })
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.query.chat", config.chat), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("set")
                        .then(literal("hud")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.set.hud", config.hud), false);
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendSuccess(new TranslationTextComponent("commands.raised.set.chat", config.chat), false);
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }
    
}