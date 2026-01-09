package com.phantomwing.rusticdelight.tags;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // Biome tags
    public static class Biomes {
        public static final TagKey<Biome> HAS_WILD_COTTON = create("has_wild_cotton");
        public static final TagKey<Biome> HAS_WILD_BELL_PEPPERS = create("has_wild_bell_peppers");
        public static final TagKey<Biome> HAS_WILD_COFFEE = create("has_wild_coffee");

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
        }
    }

    // Block tags
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
        }
    }

    // Item tags
    public static class Items {
        // Food ingredients
        public static final TagKey<Item> CALAMARI_ROLL_INGREDIENTS = tag("calamari_roll_ingredients");
        public static final TagKey<Item> CHERRY_BLOSSOM_INGREDIENTS = tag("cherry_blossom_ingredients");
        public static final TagKey<Item> SYRUP_INGREDIENTS = tag("syrup_ingredients");
        public static final TagKey<Item> COOKING_OIL_INGREDIENTS = tag("cooking_oil_ingredients");
        public static final TagKey<Item> COFFEE_INGREDIENTS = tag("coffee_ingredients");
        public static final TagKey<Item> COFFEE_FOOD_INGREDIENTS = tag("coffee_food_ingredients");
        public static final TagKey<Item> SPRING_ROLL_INGREDIENTS = tag("spring_roll_ingredients");
        public static final TagKey<Item> STUFFED_BELL_PEPPER_INGREDIENTS = tag("stuffed_bell_pepper_ingredients");
        public static final TagKey<Item> FRIED_RICE_INGREDIENTS = tag("fried_rice_ingredients");
        public static final TagKey<Item> MUSHROOM_RICE_INGREDIENTS = tag("mushroom_rice_ingredients");

        // Tags to improve compatibility
        public static final TagKey<Item> COOKING_OIL = tag("cooking_oil");
        public static final TagKey<Item> SYRUP = tag("syrup");
        public static final TagKey<Item> SWEET_LIQUIDS = tag("sweet_liquids"); // Honey, Syrup, ...

        // Combines raw and cooked eggs together in a single tag
        public static final TagKey<Item> RAW_AND_COOKED_EGGS = tag("raw_cooked_eggs");
        public static final TagKey<Item> FRUITS_AND_BERRIES = tag("fruits_berries");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
        }
    }
}