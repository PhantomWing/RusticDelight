package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.item.ItemFamily;
import net.minecraftforge.common.ForgeConfigSpec;

public class Configuration {
    public static ForgeConfigSpec COMMON_CONFIG;

    // COMMON
    public static final String SQUIDS_DROP_CALAMARI_ID = "squids_drop_calamari";
    public static ForgeConfigSpec.BooleanValue SQUIDS_DROP_CALAMARI;

    // Crop family master toggles
    public static final String ENABLE_COTTON_ID = "enable_cotton";
    public static ForgeConfigSpec.BooleanValue ENABLE_COTTON;

    public static final String ENABLE_COFFEE_ID = "enable_coffee";
    public static ForgeConfigSpec.BooleanValue ENABLE_COFFEE;

    public static final String ENABLE_BELL_PEPPERS_ID = "enable_bell_peppers";
    public static ForgeConfigSpec.BooleanValue ENABLE_BELL_PEPPERS;

    public static final String ENABLE_POTATO_SLICES_ID = "enable_potato_slices";
    public static ForgeConfigSpec.BooleanValue ENABLE_POTATO_SLICES;

    public static final String ENABLE_FRIED_FOODS_ID = "enable_fried_foods";
    public static ForgeConfigSpec.BooleanValue ENABLE_FRIED_FOODS;

    public static final String ENABLE_CHERRY_BLOSSOM_FOODS_ID = "enable_cherry_blossom_foods";
    public static ForgeConfigSpec.BooleanValue ENABLE_CHERRY_BLOSSOM_FOODS;

    public static final String ENABLE_PANCAKES_ID = "enable_pancakes";
    public static ForgeConfigSpec.BooleanValue ENABLE_PANCAKES;

    public static final String ENABLE_SYRUP_FOODS_ID = "enable_syrup_foods";
    public static ForgeConfigSpec.BooleanValue ENABLE_SYRUP_FOODS;

    public static final String CHANCE_WILD_COTTON_ID = "wild_cotton_chance";
    public static ForgeConfigSpec.IntValue CHANCE_WILD_COTTON;

    public static final String CHANCE_WILD_BELL_PEPPERS_ID = "wild_bell_peppers_chance";
    public static ForgeConfigSpec.IntValue CHANCE_WILD_BELL_PEPPERS;

    public static final String CHANCE_WILD_COFFEE_ID = "wild_coffee_chance";
    public static ForgeConfigSpec.IntValue CHANCE_WILD_COFFEE;

    public static final String CHANCE_BELL_PEPPER_BLOCK_PATCH_ID = "bell_pepper_block_patch_chance";
    public static ForgeConfigSpec.IntValue CHANCE_BELL_PEPPER_BLOCK_PATCH;

    // Villager trades
    public static final String ENABLE_VILLAGER_TRADES_ID = "enable_villager_trades";
    public static ForgeConfigSpec.BooleanValue ENABLE_VILLAGER_TRADES;

    // Wandering Trader trades
    public static final String ENABLE_WANDERING_TRADER_TRADES_ID = "enable_wandering_trader_trades";
    public static ForgeConfigSpec.BooleanValue ENABLE_WANDERING_TRADER_TRADES;

    // Potions
    public static final String ENABLE_POTIONS_ID = "enable_potions";
    public static ForgeConfigSpec.BooleanValue ENABLE_POTIONS;

    // Village farm crops
    public static final String GENERATE_VILLAGE_FARM_CROPS_ID = "generate_village_farm_crops";
    public static ForgeConfigSpec.BooleanValue GENERATE_VILLAGE_FARM_CROPS;

    // Structure chest loot
    public static final String GENERATE_RANDOM_LOOT_ID = "generate_random_loot";
    public static ForgeConfigSpec.BooleanValue GENERATE_RANDOM_LOOT;

    public static int getIntConfigurationValue(String id) {
        return switch (id) {
            case CHANCE_WILD_COTTON_ID -> Configuration.CHANCE_WILD_COTTON.get();
            case CHANCE_WILD_BELL_PEPPERS_ID -> Configuration.CHANCE_WILD_BELL_PEPPERS.get();
            case CHANCE_WILD_COFFEE_ID -> Configuration.CHANCE_WILD_COFFEE.get();
            case CHANCE_BELL_PEPPER_BLOCK_PATCH_ID -> Configuration.CHANCE_BELL_PEPPER_BLOCK_PATCH.get();
            default -> 0;
        };
    }

    public static boolean getBooleanConfigurationValue(String id) {
        return switch (id) {
            case SQUIDS_DROP_CALAMARI_ID -> Configuration.SQUIDS_DROP_CALAMARI.get();
            case ENABLE_POTIONS_ID -> Configuration.ENABLE_POTIONS.get();
            case ENABLE_VILLAGER_TRADES_ID -> Configuration.ENABLE_VILLAGER_TRADES.get();
            case ENABLE_WANDERING_TRADER_TRADES_ID -> Configuration.ENABLE_WANDERING_TRADER_TRADES.get();
            case GENERATE_VILLAGE_FARM_CROPS_ID -> Configuration.GENERATE_VILLAGE_FARM_CROPS.get();
            case GENERATE_RANDOM_LOOT_ID -> Configuration.GENERATE_RANDOM_LOOT.get();
            case ENABLE_COTTON_ID -> Configuration.ENABLE_COTTON.get();
            case ENABLE_COFFEE_ID -> Configuration.ENABLE_COFFEE.get();
            case ENABLE_BELL_PEPPERS_ID -> Configuration.ENABLE_BELL_PEPPERS.get();
            case ENABLE_POTATO_SLICES_ID -> Configuration.ENABLE_POTATO_SLICES.get();
            case ENABLE_FRIED_FOODS_ID -> Configuration.ENABLE_FRIED_FOODS.get();
            case ENABLE_CHERRY_BLOSSOM_FOODS_ID -> Configuration.ENABLE_CHERRY_BLOSSOM_FOODS.get();
            case ENABLE_PANCAKES_ID -> Configuration.ENABLE_PANCAKES.get();
            case ENABLE_SYRUP_FOODS_ID -> Configuration.ENABLE_SYRUP_FOODS.get();
            default -> false;
        };
    }

    // Maps a wild-gen chance option to its crop family's master toggle.
    public static boolean isWorldgenFeatureEnabled(String chanceId) {
        return switch (chanceId) {
            case CHANCE_WILD_COTTON_ID -> ItemFamily.COTTON.isEnabled();
            case CHANCE_WILD_COFFEE_ID -> ItemFamily.COFFEE.isEnabled();
            case CHANCE_WILD_BELL_PEPPERS_ID, CHANCE_BELL_PEPPER_BLOCK_PATCH_ID -> ItemFamily.BELL_PEPPER.isEnabled();
            default -> true;
        };
    }

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        // General settings
        SQUIDS_DROP_CALAMARI = COMMON_BUILDER.comment("Should squids drop a Calamari item? Disabling also removes all calamari items from the creative tab and disables Calamari villager trades.").define(SQUIDS_DROP_CALAMARI_ID, true);
        ENABLE_VILLAGER_TRADES = COMMON_BUILDER.comment("Should villagers trade Rustic Delight items? (May reduce chances of other trades appearing)").define(ENABLE_VILLAGER_TRADES_ID, true);
        ENABLE_WANDERING_TRADER_TRADES = COMMON_BUILDER.comment("Should the Wandering Trader sell Rustic Delight items?").define(ENABLE_WANDERING_TRADER_TRADES_ID, true);
        ENABLE_POTIONS = COMMON_BUILDER.comment("Should players be able to brew Rustic Delight potions?").define(ENABLE_POTIONS_ID, true);
        GENERATE_VILLAGE_FARM_CROPS = COMMON_BUILDER.comment("Should Rustic Delight crops (bell peppers, cotton, coffee) generate in village farm plots?").define(GENERATE_VILLAGE_FARM_CROPS_ID, true);
        GENERATE_RANDOM_LOOT = COMMON_BUILDER.comment("Should Rustic Delight items appear in structure chests? They replace an existing item (for example wheat seeds become cotton seeds) rather than being added, so chests never end up fuller than vanilla.").define(GENERATE_RANDOM_LOOT_ID, true);

        // Crop family master toggles. Disabling a family makes its entire content chain unobtainable:
        // removed from the creative tab, no wild/giant worldgen, no village-farm gen, and no trades.
        ENABLE_COTTON = COMMON_BUILDER.comment("Enable the Cotton crop family (cotton, seeds, bag, crate, wild cotton, related trades).").define(ENABLE_COTTON_ID, true);
        ENABLE_COFFEE = COMMON_BUILDER.comment("Enable the Coffee crop family (coffee beans, all coffee drinks, coffee cookie, coffee-braised beef, Haste potions, related trades).").define(ENABLE_COFFEE_ID, true);
        ENABLE_BELL_PEPPERS = COMMON_BUILDER.comment("Enable the Bell Pepper crop family (all colors, slices, roasted, rolls, stuffed, soup, pasta, giant blocks, crates, seeds, related trades).").define(ENABLE_BELL_PEPPERS_ID, true);
        ENABLE_POTATO_SLICES = COMMON_BUILDER.comment("Enable potato slices. Disabling removes potato slices (raw and baked) from the creative tab and disables their cutting-board recipes.").define(ENABLE_POTATO_SLICES_ID, true);
        ENABLE_FRIED_FOODS = COMMON_BUILDER.comment("Enable fried foods. Disabling removes Cooking Oil and everything fried with it (fried dough, dumplings, spring rolls, beignet, fried calamari/chicken/mushrooms) from the creative tab and disables their recipes.").define(ENABLE_FRIED_FOODS_ID, true);
        ENABLE_CHERRY_BLOSSOM_FOODS = COMMON_BUILDER.comment("Enable cherry blossom foods (cherry blossom roll, cookie, pancakes, cheesecake, coffee). Disabling also removes Rice Roll Royale, which needs a Cherry Blossom Roll.").define(ENABLE_CHERRY_BLOSSOM_FOODS_ID, true);
        ENABLE_PANCAKES = COMMON_BUILDER.comment("Enable pancakes (plain, honey, chocolate, cherry blossom, vegetable, pumpkin).").define(ENABLE_PANCAKES_ID, true);
        ENABLE_SYRUP_FOODS = COMMON_BUILDER.comment("Enable Syrup and the foods made from it (syrup cookie, sandwich, cheesecake, coffee). Disabling also removes plain and pumpkin pancakes, which are topped with Syrup.").define(ENABLE_SYRUP_FOODS_ID, true);

        // Crop generation rarity. Smaller value = more frequent. (To disable a crop entirely, use its enable_* toggle above.)
        CHANCE_WILD_COTTON = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .defineInRange(CHANCE_WILD_COTTON_ID, 32, 0, Integer.MAX_VALUE);
        CHANCE_WILD_BELL_PEPPERS = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .defineInRange(CHANCE_WILD_BELL_PEPPERS_ID, 15, 0, Integer.MAX_VALUE);
        CHANCE_WILD_COFFEE = COMMON_BUILDER.comment("Chance of generating clusters. Smaller value = more frequent.")
                .defineInRange(CHANCE_WILD_COFFEE_ID, 15, 0, Integer.MAX_VALUE);
        CHANCE_BELL_PEPPER_BLOCK_PATCH = COMMON_BUILDER.comment("Chance of generating a patch of bell pepper blocks in the jungle. Smaller value = more frequent (vanilla melons use 6).")
                .defineInRange(CHANCE_BELL_PEPPER_BLOCK_PATCH_ID, 16, 0, Integer.MAX_VALUE);

        // Build config
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
