package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Position;
import net.minecraft.commands.CommandSourceStack;

public class PositionArgument extends StringRepresentableArgument<Position> {

    public PositionArgument() {
        super(Position.CODEC, Position::values);
    }

    public static StringRepresentableArgument<Position> position() {
        return new PositionArgument();
    }

    public static Position getPosition(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, Position.class);
    }

}