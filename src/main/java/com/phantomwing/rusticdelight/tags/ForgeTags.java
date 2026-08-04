package com.phantomwing.rusticdelight.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ForgeTags {
    // Blocks that are efficiently mined with a Knife.
    public static final TagKey<Block> MINEABLE_WITH_KNIFE = forgeBlockTag("mineable/knife");

    public static final TagKey<Item> BERRIES = forgeItemTag("berries");

    public static final TagKey<Item> BREAD = forgeItemTag("bread");
    public static final TagKey<Item> BREAD_WHEAT = forgeItemTag("bread/wheat");

    public static final TagKey<Item> COOKED_BACON = forgeItemTag("cooked_bacon");
    public static final TagKey<Item> COOKED_BEEF = forgeItemTag("cooked_beef");
    public static final TagKey<Item> COOKED_CHICKEN = forgeItemTag("cooked_chicken");
    public static final TagKey<Item> COOKED_PORK = forgeItemTag("cooked_pork");
    public static final TagKey<Item> COOKED_MUTTON = forgeItemTag("cooked_mutton");
    public static final TagKey<Item> COOKED_EGGS = forgeItemTag("cooked_eggs");
    public static final TagKey<Item> COOKED_FISHES = forgeItemTag("cooked_fishes");
    public static final TagKey<Item> COOKED_FISHES_COD = forgeItemTag("cooked_fishes/cod");
    public static final TagKey<Item> COOKED_FISHES_SALMON = forgeItemTag("cooked_fishes/salmon");
    public static final TagKey<Item> COOKED_FISHES_CALAMARI = forgeItemTag("cooked_fishes/calamari");

    public static final TagKey<Item> COOKIES = forgeItemTag("cookies");

    public static final TagKey<Item> DOUGH = forgeItemTag("dough");
    public static final TagKey<Item> DOUGH_WHEAT = forgeItemTag("dough/wheat");

    public static final TagKey<Item> EGGS = forgeItemTag("eggs");

    public static final TagKey<Item> GRAIN = forgeItemTag("grain");
    public static final TagKey<Item> GRAIN_WHEAT = forgeItemTag("grain/wheat");
    public static final TagKey<Item> GRAIN_RICE = forgeItemTag("grain/rice");

    public static final TagKey<Item> MILK = forgeItemTag("milk");
    public static final TagKey<Item> MILK_BUCKET = forgeItemTag("milk/milk");
    public static final TagKey<Item> MILK_BOTTLE = forgeItemTag("milk/milk_bottle");

    public static final TagKey<Item> PASTA = forgeItemTag("pasta");
    public static final TagKey<Item> PASTA_RAW_PASTA = forgeItemTag("pasta/raw_pasta");

    public static final TagKey<Item> RAW_BACON = forgeItemTag("raw_bacon");
    public static final TagKey<Item> RAW_BEEF = forgeItemTag("raw_beef");
    public static final TagKey<Item> RAW_CHICKEN = forgeItemTag("raw_chicken");
    public static final TagKey<Item> RAW_PORK = forgeItemTag("raw_pork");
    public static final TagKey<Item> RAW_MUTTON = forgeItemTag("raw_mutton");
    public static final TagKey<Item> RAW_FISHES = forgeItemTag("raw_fishes");
    public static final TagKey<Item> RAW_FISHES_COD = forgeItemTag("raw_fishes/cod");
    public static final TagKey<Item> RAW_FISHES_SALMON = forgeItemTag("raw_fishes/salmon");
    public static final TagKey<Item> RAW_FISHES_TROPICAL = forgeItemTag("raw_fishes/tropical_fish");
    public static final TagKey<Item> RAW_FISHES_CALAMARI = forgeItemTag("raw_fishes/calamari");

    public static final TagKey<Item> SALAD_INGREDIENTS = forgeItemTag("salad_ingredients");
    public static final TagKey<Item> SALAD_INGREDIENTS_CABBAGE = forgeItemTag("salad_ingredients/cabbage");

    // Seeds
    public static final TagKey<Item> SEEDS = forgeItemTag("seeds");
    public static final TagKey<Item> SEEDS_CANOLA = forgeItemTag("seeds/canola");
    public static final TagKey<Item> SEEDS_SUNFLOWER = forgeItemTag("seeds/sunflower");
    public static final TagKey<Item> SEEDS_CABBAGE = forgeItemTag("seeds/cabbage");
    public static final TagKey<Item> SEEDS_RICE = forgeItemTag("seeds/rice");
    public static final TagKey<Item> SEEDS_TOMATO = forgeItemTag("seeds/tomato");
    public static final TagKey<Item> SEEDS_BELL_PEPPER = forgeItemTag("seeds/bell_pepper");
    public static final TagKey<Item> SEEDS_COTTON = forgeItemTag("seeds/cotton");
    public static final TagKey<Item> SEEDS_COFFEE = forgeItemTag("seeds/coffee");
    public static final TagKey<Item> SEEDS_COFFEE_BEANS = forgeItemTag("seeds/coffee_beans");

    // Crops
    public static final TagKey<Item> CROPS = forgeItemTag("crops");
    public static final TagKey<Item> CROPS_CABBAGE = forgeItemTag("crops/cabbage");
    public static final TagKey<Item> CROPS_ONION = forgeItemTag("crops/onion");
    public static final TagKey<Item> CROPS_RICE = forgeItemTag("crops/rice");
    public static final TagKey<Item> CROPS_TOMATO = forgeItemTag("crops/tomato");
    public static final TagKey<Item> CROPS_COTTON = forgeItemTag("crops/cotton");
    public static final TagKey<Item> CROPS_BELL_PEPPER = forgeItemTag("crops/bell_pepper");
    public static final TagKey<Item> CROPS_COFFEE = forgeItemTag("crops/coffee");
    public static final TagKey<Item> CROPS_COFFEE_BEANS = forgeItemTag("crops/coffee_beans");

    // Vegetables
    public static final TagKey<Item> VEGETABLES = forgeItemTag("vegetables");
    public static final TagKey<Item> VEGETABLES_BEETROOT = forgeItemTag("vegetables/beetroot");
    public static final TagKey<Item> VEGETABLES_CARROT = forgeItemTag("vegetables/carrot");
    public static final TagKey<Item> VEGETABLES_ONION = forgeItemTag("vegetables/onion");
    public static final TagKey<Item> VEGETABLES_POTATO = forgeItemTag("vegetables/potato");
    public static final TagKey<Item> VEGETABLES_TOMATO = forgeItemTag("vegetables/tomato");
    public static final TagKey<Item> VEGETABLES_BELL_PEPPER = forgeItemTag("vegetables/bell_pepper");

    // Tools
    public static final TagKey<Item> TOOLS = forgeItemTag("tools");
    public static final TagKey<Item> TOOLS_AXES = forgeItemTag("tools/axes");
    public static final TagKey<Item> TOOLS_KNIVES = forgeItemTag("tools/knives");
    public static final TagKey<Item> TOOLS_PICKAXES = forgeItemTag("tools/pickaxes");
    public static final TagKey<Item> TOOLS_SHOVELS = forgeItemTag("tools/shovels");

    // Storage blocks
    public static final TagKey<Block> STORAGE_BLOCKS_COTTON_SEEDS = forgeBlockTag("storage_blocks/cotton_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_SEEDS = forgeBlockTag("storage_blocks/bell_pepper_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_COFFEE_BEANS = forgeBlockTag("storage_blocks/coffee_beans");
    public static final TagKey<Block> STORAGE_BLOCKS_COFFEE = forgeBlockTag("storage_blocks/coffee"); // Alternate tag for Coffee Beans, for better compatibility
    public static final TagKey<Block> STORAGE_BLOCKS_ROASTED_COFFEE_BEANS = forgeBlockTag("storage_blocks/roasted_coffee_beans");
    public static final TagKey<Block> STORAGE_BLOCKS_COTTON = forgeBlockTag("storage_blocks/cotton");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_RED = forgeBlockTag("storage_blocks/bell_pepper_red");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_GREEN = forgeBlockTag("storage_blocks/bell_pepper_green");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_YELLOW = forgeBlockTag("storage_blocks/bell_pepper_yellow");
    public static final TagKey<Block> STORAGE_BLOCKS_PALE_BELL_PEPPER_SEEDS = forgeBlockTag("storage_blocks/pale_bell_pepper_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_DARK_BELL_PEPPER_SEEDS = forgeBlockTag("storage_blocks/dark_bell_pepper_seeds");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_ORANGE = forgeBlockTag("storage_blocks/bell_pepper_orange");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_WHITE = forgeBlockTag("storage_blocks/bell_pepper_white");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_PINK = forgeBlockTag("storage_blocks/bell_pepper_pink");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_BLUE = forgeBlockTag("storage_blocks/bell_pepper_blue");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_PURPLE = forgeBlockTag("storage_blocks/bell_pepper_purple");
    public static final TagKey<Block> STORAGE_BLOCKS_BELL_PEPPER_BLACK = forgeBlockTag("storage_blocks/bell_pepper_black");
    public static final TagKey<Block> STORAGE_BLOCKS_CALAMARI = forgeBlockTag("storage_blocks/calamari");

    // Storage blocks (items)
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COTTON_SEEDS = forgeItemTag("storage_blocks/cotton_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS = forgeItemTag("storage_blocks/bell_pepper_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COFFEE_BEANS = forgeItemTag("storage_blocks/coffee_beans");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COFFEE = forgeItemTag("storage_blocks/coffee"); // Alternate tag for Coffee Beans, for better compatibility
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS = forgeItemTag("storage_blocks/roasted_coffee_beans");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_COTTON = forgeItemTag("storage_blocks/cotton");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED = forgeItemTag("storage_blocks/bell_pepper_red");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN = forgeItemTag("storage_blocks/bell_pepper_green");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW = forgeItemTag("storage_blocks/bell_pepper_yellow");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_PALE_BELL_PEPPER_SEEDS = forgeItemTag("storage_blocks/pale_bell_pepper_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_DARK_BELL_PEPPER_SEEDS = forgeItemTag("storage_blocks/dark_bell_pepper_seeds");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_ORANGE = forgeItemTag("storage_blocks/bell_pepper_orange");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_WHITE = forgeItemTag("storage_blocks/bell_pepper_white");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_PINK = forgeItemTag("storage_blocks/bell_pepper_pink");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLUE = forgeItemTag("storage_blocks/bell_pepper_blue");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_PURPLE = forgeItemTag("storage_blocks/bell_pepper_purple");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLACK = forgeItemTag("storage_blocks/bell_pepper_black");
    public static final TagKey<Item> STORAGE_BLOCKS_ITEM_CALAMARI = forgeItemTag("storage_blocks/calamari");

    // Forge declares no constants for these, but biome mods populate them by convention, so wild
    // crops reach modded forests and jungles too. Harmless when nothing populates them.
    public static final TagKey<Biome> IS_FOREST = forgeBiomeTag("is_forest");
    public static final TagKey<Biome> IS_JUNGLE = forgeBiomeTag("is_jungle");

    private static TagKey<Block> forgeBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }

    private static TagKey<Biome> forgeBiomeTag(String path) {
        return TagKey.create(Registries.BIOME, new ResourceLocation("forge", path));
    }
}
