package com.phantomwing.rusticdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
    public static ModConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String CATEGORY_SETTINGS = "settings";
    public static ModConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Game settings").push(CATEGORY_SETTINGS);
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Rustic Delight adds Calamari and various associated food items. Should squids and glow squids drop Calamari when defeated?")
                .define("squidsDropCalamari", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
