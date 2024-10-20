package com.phantomwing.rusticdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
    public static ModConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String CATEGORY_SETTINGS = "settings";
    public static final String SQUIDS_DROP_CALAMARI_ID = "squids_drop_calamari";
    public static ModConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    public static final String CATEGORY_WORLD = "world";

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_COTTON;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_BELL_PEPPERS;

    public static final String CHANCE_WILD_COFFEE_ID = "wild_coffee_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_COFFEE;

    public static int getIntConfigurationValue(String id) {
        return switch (id) {
            case CHANCE_WILD_COTTON_ID -> Configuration.CHANCE_WILD_COTTON.get();
            case CHANCE_WILD_BELL_PEPPERS_ID -> Configuration.CHANCE_WILD_BELL_PEPPERS.get();
            case CHANCE_WILD_COFFEE_ID -> Configuration.CHANCE_WILD_COFFEE.get();
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
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        // General settings
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Should squids drop a Calamari item? Also disables villager trades for Calamari.").define(SQUIDS_DROP_CALAMARI_ID, true);

        // Cotton settings
        CHANCE_WILD_COTTON = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_COTTON_ID, 96, 0, Integer.MAX_VALUE);

        // Bell Pepper settings
        CHANCE_WILD_BELL_PEPPERS = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_BELL_PEPPERS_ID, 15, 0, Integer.MAX_VALUE);

        // Coffee settings
        CHANCE_WILD_COFFEE = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_COFFEE_ID, 15, 0, Integer.MAX_VALUE);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
