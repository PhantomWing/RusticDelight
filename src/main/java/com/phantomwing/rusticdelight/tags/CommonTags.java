package com.phantomwing.rusticdelight.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {
    public static final TagKey<Item> FOODS_RAW_CALAMARI = commonItemTag("foods/raw_calamari");
    public static final TagKey<Item> FOODS_COOKED_CALAMARI = commonItemTag("foods/cooked_calamari");
    public static final TagKey<Item> FOODS_RAW_SQUID = commonItemTag("foods/raw_squid");
    public static final TagKey<Item> FOODS_COOKED_SQUID = commonItemTag("foods/cooked_squid");
    public static final TagKey<Item> FOODS_MILK = commonItemTag("foods/milk");
    public static final TagKey<Item> FOODS_DOUGH = commonItemTag("foods/dough");
    public static final TagKey<Item> FOODS_PASTA = commonItemTag("foods/pasta");
    public static final TagKey<Item> FOODS_RAW_BEEF = commonItemTag("foods/raw_beef");
    public static final TagKey<Item> FOODS_RAW_CHICKEN = commonItemTag("foods/raw_chicken");
    public static final TagKey<Item> FOODS_LEAFY_GREEN = commonItemTag("foods/leafy_green");
    public static final TagKey<Item> FOODS_POTATO = commonItemTag("foods/potato");
    public static final TagKey<Item> FOODS_CARROT = commonItemTag("foods/carrot");
    public static final TagKey<Item> FOODS_ONION = commonItemTag("foods/onion");
    public static final TagKey<Item> FOODS_TOMATO = commonItemTag("foods/tomato");
    public static final TagKey<Item> FOODS_BELL_PEPPER = commonItemTag("foods/bell_pepper");
    public static final TagKey<Item> FOODS_COOKIE = commonItemTag("foods/cookie");

    public static final TagKey<Item> CROPS_COTTON = commonItemTag("crops/cotton");
    public static final TagKey<Item> CROPS_BELL_PEPPER = commonItemTag("crops/bell_pepper");
    public static final TagKey<Item> CROPS_COFFEE_BEANS = commonItemTag("crops/coffee_beans");
    public static final TagKey<Item> CROPS_COFFEE = commonItemTag("crops/coffee"); // Duplicate tag, because some mods call it coffee instead of coffee beans
    public static final TagKey<Item> CROPS_RICE = commonItemTag("crops/rice");

    public static final TagKey<Item> TOOLS_KNIFE = commonItemTag("tools/knife");

    // Storage blocks
    public static final TagKey<Block> STORAGE_BLOCKS_COTTON_SEEDS = commonBlockTag("storage_blocks/cotton_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_SEEDS = commonBlockTag("storage_blocks/bell_pepper_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_COFFEE_BEANS = commonBlockTag("storage_blocks/coffee_beans");
    public static final TagKey<Block> STORAGE_BLOCKS_COFFEE = commonBlockTag("storage_blocks/coffee"); // Alternate tag for Coffee Beans, for better compatibility
    public static final TagKey<Block> STORAGE_BLOCKS_ROASTED_COFFEE_BEANS = commonBlockTag("storage_blocks/roasted_coffee_beans");
    public static final TagKey<Block> STORAGE_BLOCKS_COTTON = commonBlockTag("storage_blocks/cotton");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_RED = commonBlockTag("storage_blocks/bell_pepper_red");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_GREEN = commonBlockTag("storage_blocks/bell_pepper_green");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_YELLOW = commonBlockTag("storage_blocks/bell_pepper_yellow");

    // Storage blocks (items)
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COTTON_SEEDS = commonItemTag("storage_blocks/cotton_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS = commonItemTag("storage_blocks/bell_pepper_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COFFEE_BEANS = commonItemTag("storage_blocks/coffee_beans");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COFFEE = commonItemTag("storage_blocks/coffee"); // Alternate tag for Coffee Beans, for better compatibility
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS = commonItemTag("storage_blocks/roasted_coffee_beans");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COTTON = commonItemTag("storage_blocks/cotton");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED = commonItemTag("storage_blocks/bell_pepper_red");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN = commonItemTag("storage_blocks/bell_pepper_green");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW = commonItemTag("storage_blocks/bell_pepper_yellow");

    // Seeds
    public static final TagKey<Item> SEEDS_COTTON = commonItemTag("seeds/cotton");
    public static final TagKey<Item> SEEDS_BELL_PEPPER = commonItemTag("seeds/bell_pepper");
    public static final TagKey<Item> SEEDS_COFFEE_BEANS = commonItemTag("seeds/coffee_beans");
    public static final TagKey<Item> SEEDS_COFFEE = commonItemTag("seeds/coffee"); // Duplicate tag, because some mods call it coffee instead of coffee beans

    // Compatibility
    public static final TagKey<Item> SEEDS_CANOLA = commonItemTag("seeds/canola");
    public static final TagKey<Item> SEEDS_SUNFLOWER = commonItemTag("seeds/sunflower");

    private static TagKey<Block> commonBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    private static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
}
