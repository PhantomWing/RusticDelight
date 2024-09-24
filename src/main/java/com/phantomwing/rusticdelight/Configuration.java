package com.phantomwing.rusticdelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configuration {
    public static ForgeConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String CATEGORY_SETTINGS = "settings";
    public static ForgeConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Game settings").push(CATEGORY_SETTINGS);
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Rustic Delight adds Calamari and various associated food items. Should squids and glow squids drop Calamari when defeated?")
                .define("squidsDropCalamari", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
