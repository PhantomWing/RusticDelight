package com.phantomwing.rusticdelight.tag;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tah(String  name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(RusticDelight.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CALAMARI_ROLL_INGREDIENTS = tag("calamari_roll_ingredients");
        public static final TagKey<Item> CHERRY_BLOSSOM_INGREDIENTS = tag("cherry_blossom_ingredients");
        public static final TagKey<Item> FRIED_RICE_INGREDIENTS = tag("fried_rice_ingredients");
        public static final TagKey<Item> COOKING_OIL_INGREDIENTS = tag("cooking_oil_ingredients");
        public static final TagKey<Item> COOKING_OIL = tag("cooking_oil");

        private static TagKey<Item> tag(String  name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(RusticDelight.MOD_ID, name));
        }
    }
}
