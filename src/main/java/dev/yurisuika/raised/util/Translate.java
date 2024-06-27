package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Sync;

public class Translate {

    public static void start(Element element) {
        RenderSystem.pushMatrix();
        RenderSystem.translated(Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getX(), Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getY(), element == Element.CHAT ? 300 : 0);
    }

    public static void end() {
        RenderSystem.popMatrix();
    }

}