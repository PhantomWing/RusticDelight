package com.phantomwing.rusticdelight.tags;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagManager {
    // Block tags
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
        }
    }

    // Item tags
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
        }
    }
}
