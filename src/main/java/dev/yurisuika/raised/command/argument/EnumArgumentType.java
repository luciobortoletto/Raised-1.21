package dev.yurisuika.raised.command.argument;

import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.command.CommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.StringIdentifiable;

public class EnumArgumentType<T extends Enum<T>> implements ArgumentType<T> {

    public static final DynamicCommandExceptionType INVALID_ENUM_EXCEPTION = new DynamicCommandExceptionType(value -> new TranslatableText("argument.raised.enum.invalid", value));
    public final Codec<T> codec;
    public final Supplier<T[]> valuesSupplier;

    public EnumArgumentType(Codec<T> codec, Supplier<T[]> valuesSupplier) {
        this.codec = codec;
        this.valuesSupplier = valuesSupplier;
    }

    @Override
    public T parse(StringReader stringReader) throws CommandSyntaxException {
        String string = stringReader.readUnquotedString();
        return codec.parse(JsonOps.INSTANCE, new JsonPrimitive(string)).result().orElseThrow(() -> INVALID_ENUM_EXCEPTION.create(string));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(Arrays.stream((Enum[])valuesSupplier.get()).map(enum_ -> ((StringIdentifiable)enum_).asString()).collect(Collectors.toList()), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.stream((Enum[])valuesSupplier.get()).map(enum_ -> ((StringIdentifiable)enum_).asString()).limit(2L).collect(Collectors.toList());
    }

}

