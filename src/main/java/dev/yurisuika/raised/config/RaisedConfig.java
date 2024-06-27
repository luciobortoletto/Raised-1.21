package dev.yurisuika.raised.config;

import dev.yurisuika.raised.util.config.option.*;
import dev.yurisuika.raised.util.type.*;

public class RaisedConfig {

    public Elements elements = new Elements(
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
    );

}