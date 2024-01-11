package dev.yurisuika.raised.client.gui;

import net.minecraft.client.util.math.MatrixStack;

public class RaisedGui {

    public static void start(MatrixStack matrices, int x, int y, int z) {
        matrices.translate(x, -y, +z);
    }

    public static void end(MatrixStack matrices, int x, int y, int z) {
        matrices.translate(x, +y, -z);
    }

}