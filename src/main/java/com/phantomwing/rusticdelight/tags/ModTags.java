package com.phantomwing.rusticdelight.tags;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // Block tags
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RusticDelight.MOD_ID, name));
        }
    }

    // Item tags
    public static class Items {
        public static final TagKey<Item> COOKING_OIL_INGREDIENTS = tag("cooking_oil_ingredients");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RusticDelight.MOD_ID, name));
        }
    }
}