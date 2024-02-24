package dev.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setHud(2);
                                    setChat(0);
                                    setShare(true);
                                    setSupport(true);
                                    setSync(false);
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(literal("hud")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.hud.hud.query", config.value.hud));
                                    return 1;
                                })
                                .then(argument("hud", IntegerArgumentType.integer(0))
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "hud"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.hud.hud.set", config.value.hud));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.hud.chat.query", config.value.chat));
                                    return 1;
                                })
                                .then(argument("hud", IntegerArgumentType.integer(0))
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "hud"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.hud.chat.set", config.value.chat));
                                            return 1;
                                        })
                                )
                        )
                )
                .then(literal("toggle")
                        .then(literal("share")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.share.query", config.toggle.share));
                                    return 1;
                                })
                                .then(argument("hud", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setShare(BoolArgumentType.getBool(context, "hud"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.share.set", config.toggle.share));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("support")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.support.query", config.toggle.support));
                                    return 1;
                                })
                                .then(argument("hud", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setSupport(BoolArgumentType.getBool(context, "hud"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.support.set", config.toggle.support));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("sync")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.sync.query", config.toggle.sync));
                                    return 1;
                                })
                                .then(argument("hud", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setSync(BoolArgumentType.getBool(context, "hud"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.toggle.sync.set", config.toggle.sync));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}