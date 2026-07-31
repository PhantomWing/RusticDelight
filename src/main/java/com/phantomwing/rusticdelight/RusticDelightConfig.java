package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.item.ItemFamily;
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

    public static final String GENERATE_VILLAGE_FARM_CROPS_ID = "generate_village_farm_crops";
    public boolean generate_village_farm_crops = true;

    public static final String GENERATE_RANDOM_LOOT_ID = "generate_random_loot";
    public boolean generate_random_loot = true;

    // Content family master toggles. Disabling a family makes its entire chain unobtainable:
    // removed from the creative tab, no worldgen, no village-farm gen, and no trades.
    public static final String ENABLE_COTTON_ID = "enable_cotton";
    public boolean enable_cotton = true;

    public static final String ENABLE_COFFEE_ID = "enable_coffee";
    public boolean enable_coffee = true;

    public static final String ENABLE_BELL_PEPPERS_ID = "enable_bell_peppers";
    public boolean enable_bell_peppers = true;

    public static final String ENABLE_POTATO_SLICES_ID = "enable_potato_slices";
    public boolean enable_potato_slices = true;

    public static final String ENABLE_FRIED_FOODS_ID = "enable_fried_foods";
    public boolean enable_fried_foods = true;

    public static final String ENABLE_CHERRY_BLOSSOM_FOODS_ID = "enable_cherry_blossom_foods";
    public boolean enable_cherry_blossom_foods = true;

    public static final String ENABLE_PANCAKES_ID = "enable_pancakes";
    public boolean enable_pancakes = true;

    public static final String ENABLE_SYRUP_FOODS_ID = "enable_syrup_foods";
    public boolean enable_syrup_foods = true;

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public int wild_cotton_chance = 32;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public int wild_bell_peppers_chance = 15;

    public static final String CHANCE_WILD_COFFEE_ID = "wild_coffee_chance";
    public int wild_coffee_chance = 15;

    public static final String CHANCE_BELL_PEPPER_BLOCK_PATCH_ID = "bell_pepper_block_patch_chance";
    public int bell_pepper_block_patch_chance = 16;

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
            case CHANCE_BELL_PEPPER_BLOCK_PATCH_ID -> config.bell_pepper_block_patch_chance;
            default -> 0;
        };
    }

    /** Maps a wild-gen chance option to its crop family's master toggle. */
    public static boolean isWorldgenFeatureEnabled(String chanceId) {
        return switch (chanceId) {
            case CHANCE_WILD_COTTON_ID -> ItemFamily.COTTON.isEnabled();
            case CHANCE_WILD_COFFEE_ID -> ItemFamily.COFFEE.isEnabled();
            case CHANCE_WILD_BELL_PEPPERS_ID, CHANCE_BELL_PEPPER_BLOCK_PATCH_ID -> ItemFamily.BELL_PEPPER.isEnabled();
            default -> true;
        };
    }

    public static boolean getBooleanConfigurationValue(String id) {
        RusticDelightConfig config = RusticDelightConfig.get();

        return switch (id) {
            case SQUIDS_DROP_CALAMARI_ID -> config.squids_drop_calamari;
            case ENABLE_POTIONS_ID -> config.enable_potions;
            case ENABLE_VILLAGER_TRADES_ID ->  config.enable_villager_trades;
            case ENABLE_WANDERING_TRADER_TRADES_ID ->  config.enable_wandering_trader_trades;
            case GENERATE_VILLAGE_FARM_CROPS_ID -> config.generate_village_farm_crops;
            case GENERATE_RANDOM_LOOT_ID -> config.generate_random_loot;
            case ENABLE_COTTON_ID -> config.enable_cotton;
            case ENABLE_COFFEE_ID -> config.enable_coffee;
            case ENABLE_BELL_PEPPERS_ID -> config.enable_bell_peppers;
            case ENABLE_POTATO_SLICES_ID -> config.enable_potato_slices;
            case ENABLE_FRIED_FOODS_ID -> config.enable_fried_foods;
            case ENABLE_CHERRY_BLOSSOM_FOODS_ID -> config.enable_cherry_blossom_foods;
            case ENABLE_PANCAKES_ID -> config.enable_pancakes;
            case ENABLE_SYRUP_FOODS_ID -> config.enable_syrup_foods;
            default -> false;
        };
    }
}