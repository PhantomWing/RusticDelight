package com.phantomwing.rusticdelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configuration {
    public static ForgeConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String CATEGORY_SETTINGS = "settings";
    public static final String SQUIDS_DROP_CALAMARI_ID = "squids_drop_calamari";
    public static ForgeConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    public static final String CATEGORY_WORLD = "world";

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public static ForgeConfigSpec.IntValue CHANCE_WILD_COTTON;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public static ForgeConfigSpec.IntValue CHANCE_WILD_BELL_PEPPERS;

    public static int getIntConfigurationValue(String id) {
        return switch (id) {
            case CHANCE_WILD_COTTON_ID -> Configuration.CHANCE_WILD_COTTON.get();
            case CHANCE_WILD_BELL_PEPPERS_ID -> Configuration.CHANCE_WILD_BELL_PEPPERS.get();
            default -> 0;
        };
    }

    public static boolean getBooleanConfigurationValue(String id) {
        return switch (id) {
            case SQUIDS_DROP_CALAMARI_ID -> Configuration.SQUIDS_DROP_CALAMARI.get();
            default -> false;
        };
    }

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        // General settings
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Should squids drop a Calamari item? Also disables villager trades for Calamari.").define(SQUIDS_DROP_CALAMARI_ID, true);

        // Cotton settings
        CHANCE_WILD_COTTON = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_COTTON_ID, 128, 0, Integer.MAX_VALUE);

        // Bell Pepper settings
        CHANCE_WILD_BELL_PEPPERS = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_BELL_PEPPERS_ID, 20, 0, Integer.MAX_VALUE);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
