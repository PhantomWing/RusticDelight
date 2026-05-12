package com.phantomwing.rusticdelight;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = RusticDelight.MOD_ID)
public class RusticDelightConfig implements ConfigData {
    public static final String SQUIDS_DROP_CALAMARI_ID = "squids_drop_calamari";
    public boolean squids_drop_calamari = true;

    public static final String ENABLE_VILLAGER_TRADES_ID = "enable_villager_trades";
    public boolean enable_villager_trades = true;

    public static final String ENABLE_WANDERING_TRADER_TRADES_ID = "enable_wandering_trader_trades";
    public boolean enable_wandering_trader_trades = true;

    public static final String ENABLE_POTIONS_ID = "enable_potions";
    public boolean enable_potions = true;

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public int wild_cotton_chance = 32;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public int wild_bell_peppers_chance = 32;

    public static final String CHANCE_WILD_COFFEE_ID = "wild_coffee_chance";
    public int wild_coffee_chance = 32;

    public static RusticDelightConfig get() {
        return AutoConfig.getConfigHolder(RusticDelightConfig.class).getConfig();
    }

    public static void register() {
        AutoConfig.register(RusticDelightConfig.class, GsonConfigSerializer::new);
    }

    public static int getIntConfigurationValue(String id) {
        RusticDelightConfig config = RusticDelightConfig.get();

        return switch (id) {
            case CHANCE_WILD_COTTON_ID -> config.wild_cotton_chance;
            case CHANCE_WILD_BELL_PEPPERS_ID -> config.wild_bell_peppers_chance;
            case CHANCE_WILD_COFFEE_ID -> config.wild_coffee_chance;
            default -> 0;
        };
    }

    public static boolean getBooleanConfigurationValue(String id) {
        RusticDelightConfig config = RusticDelightConfig.get();

        return switch (id) {
            case SQUIDS_DROP_CALAMARI_ID -> config.squids_drop_calamari;
            case ENABLE_POTIONS_ID -> config.enable_potions;
            case ENABLE_VILLAGER_TRADES_ID ->  config.enable_villager_trades;
            case ENABLE_WANDERING_TRADER_TRADES_ID ->  config.enable_wandering_trader_trades;
            // Per-feature gates: feature is enabled when its chance is > 0.
            case CHANCE_WILD_COTTON_ID -> config.wild_cotton_chance > 0;
            case CHANCE_WILD_BELL_PEPPERS_ID -> config.wild_bell_peppers_chance > 0;
            case CHANCE_WILD_COFFEE_ID -> config.wild_coffee_chance > 0;
            default -> false;
        };
    }
}