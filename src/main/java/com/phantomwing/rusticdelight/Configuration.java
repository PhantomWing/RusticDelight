package com.phantomwing.rusticdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
    public static ModConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String SQUIDS_DROP_CALAMARI_ID = "squids_drop_calamari";
    public static ModConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_COTTON;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_BELL_PEPPERS;

    public static final String CHANCE_WILD_COFFEE_ID = "wild_coffee_chance";
    public static ModConfigSpec.IntValue CHANCE_WILD_COFFEE;

    // Villager trades
    public static final String ENABLE_VILLAGER_TRADES_ID = "enable_villager_trades";
    public static ModConfigSpec.BooleanValue ENABLE_VILLAGER_TRADES;

    // Wandering Trader trades
    public static final String ENABLE_WANDERING_TRADER_TRADES_ID = "enable_wandering_trader_trades";
    public static ModConfigSpec.BooleanValue ENABLE_WANDERING_TRADER_TRADES;

    // Potions
    public static final String ENABLE_POTIONS_ID = "enable_potions";
    public static ModConfigSpec.BooleanValue ENABLE_POTIONS;

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
            case ENABLE_POTIONS_ID -> Configuration.ENABLE_POTIONS.get();
            case ENABLE_VILLAGER_TRADES_ID -> Configuration.ENABLE_VILLAGER_TRADES.get();
            case ENABLE_WANDERING_TRADER_TRADES_ID -> Configuration.ENABLE_WANDERING_TRADER_TRADES.get();
            default -> false;
        };
    }

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        // General settings
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Should squids drop a Calamari item? Also disables villager trades for Calamari.").define(SQUIDS_DROP_CALAMARI_ID, true);
        ENABLE_VILLAGER_TRADES = COMMON_BUILDER.comment("Should villagers trade Rustic Delight items? (May reduce chances of other trades appearing)").define(ENABLE_VILLAGER_TRADES_ID, true);
        ENABLE_WANDERING_TRADER_TRADES = COMMON_BUILDER.comment("Should the Wandering Trader sell Rustic Delight items?").define(ENABLE_WANDERING_TRADER_TRADES_ID, true);
        ENABLE_POTIONS = COMMON_BUILDER.comment("Should players be able to brew Rustic Delight potions?").define(ENABLE_POTIONS_ID, true);

        // Crop settings
        CHANCE_WILD_COTTON = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_COTTON_ID, 96, 0, Integer.MAX_VALUE);
        CHANCE_WILD_BELL_PEPPERS = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_BELL_PEPPERS_ID, 15, 0, Integer.MAX_VALUE);
        CHANCE_WILD_COFFEE = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent. Provide zero to disable generation.")
                .defineInRange(CHANCE_WILD_COFFEE_ID, 15, 0, Integer.MAX_VALUE);

        // Build config
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
