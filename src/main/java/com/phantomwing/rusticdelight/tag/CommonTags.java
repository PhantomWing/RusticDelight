package com.phantomwing.rusticdelight.tag;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CommonTags {
    public static final String COMMON_ID = "c";

    public static final TagKey<Item> CROPS = commonItemTag("crops");
    public static final TagKey<Item> SEEDS = commonItemTag("seeds");
    public static final TagKey<Item> FOODS_RAW_FISH = commonItemTag("foods/raw_fish");
    public static final TagKey<Item> FOODS_COOKED_FISH = commonItemTag("foods/cooked_fish");
    public static final TagKey<Item> FOODS_FRUIT = commonItemTag("foods/fruit");
    public static final TagKey<Item> FOODS_BERRY = commonItemTag("foods/berry");
    public static final TagKey<Item> FOODS_VEGETABLE = commonItemTag("foods/vegetable");
    public static final TagKey<Item> FOODS_RAW_CALAMARI = commonItemTag("foods/raw_calamari");
    public static final TagKey<Item> FOODS_COOKED_CALAMARI = commonItemTag("foods/cooked_calamari");
    public static final TagKey<Item> FOODS_MILK = commonItemTag("foods/milk");
    public static final TagKey<Item> FOODS_WATER = commonItemTag("foods/water");
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

    public static final TagKey<Item> CROPS_COTTON = commonItemTag("crops/cotton");
    public static final TagKey<Item> CROPS_BELL_PEPPER = commonItemTag("crops/bell_pepper");
    public static final TagKey<Item> CROPS_COFFEE = commonItemTag("crops/coffee");
    public static final TagKey<Item> CROPS_RICE = commonItemTag("crops/rice");

    public static final TagKey<Item> TOOLS_KNIFE = commonItemTag("tools/knife");

    public static final TagKey<Block> STORAGE_BLOCKS = commonBlockTag("storage_blocks");

    private static TagKey<Block> commonBlockTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(COMMON_ID, name));
    }

    private static TagKey<Item> commonItemTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(COMMON_ID, name));
    }
}
