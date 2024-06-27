package dev.yurisuika.raised.util.config;

import dev.yurisuika.raised.util.config.option.*;
import dev.yurisuika.raised.util.type.*;

public class Option {

    public static Elements getElements() {
        return Config.config.elements;
    }

    public static void setElements(Elements elements) {
        Config.config.elements = elements;
        Config.saveConfig();
    }

    public static Properties getProperties(Element element) {
        if (element.equals(Element.HOTBAR)) {
            return getElements().getHotbar();
        } else if (element.equals(Element.CHAT)) {
            return getElements().getChat();
        } else if (element.equals(Element.BOSSBAR)) {
            return getElements().getBossbar();
        } else if (element.equals(Element.SIDEBAR)) {
            return getElements().getSidebar();
        } else if (element.equals(Element.EFFECTS)) {
            return getElements().getEffects();
        } else if (element.equals(Element.PLAYERS)) {
            return getElements().getPlayers();
        } else if (element.equals(Element.TOASTS)) {
            return getElements().getToasts();
        } else {
            return getElements().getOther();
        }
    }

    public static int getX(Element element) {
        return getProperties(element).getX();
    }

    public static void setX(Element element, int x) {
        getProperties(element).x = x;
        Config.saveConfig();
    }

    public static int getY(Element element) {
        return getProperties(element).getY();
    }

    public static void setY(Element element, int y) {
        getProperties(element).y = y;
        Config.saveConfig();
    }

    public static Position getPosition(Element element) {
        return getProperties(element).getPosition();
    }

    public static void setPosition(Element element, Position position) {
        getProperties(element).position = position;
        Config.saveConfig();
    }

    public static Sync getSync(Element element) {
        return getProperties(element).getSync();
    }

    public static void setSync(Element element, Sync sync) {
        getProperties(element).sync = sync;
        Config.saveConfig();
    }

}