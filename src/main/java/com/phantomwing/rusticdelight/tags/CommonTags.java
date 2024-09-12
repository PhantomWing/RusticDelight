package com.phantomwing.rusticdelight.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {
    public static final TagKey<Item> COOKING_OIL_INGREDIENTS = commonItemTag("cooking_oil_ingredients");
    public static final TagKey<Item> FOODS_RAW_CALAMARI = commonItemTag("foods/raw_calamari");
    public static final TagKey<Item> FOODS_COOKED_CALAMARI = commonItemTag("foods/cooked_calamari");

    private static TagKey<Block> commonBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    private static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
}
