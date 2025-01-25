package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;

public class ComposterHelper {
    private static void registerCompostableItems (float chance, ItemConvertible ...items) {
        for (ItemConvertible item : items) {
            ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item, chance);
        }
    }

    public static void registerCompostableItems() {
        // 30% chance
        registerCompostableItems(0.3f,
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS
        );

        // 50% chance
        registerCompostableItems(0.5f,
                ModItems.COTTON_BOLL,
                ModItems.POTATO_SLICES,
                ModItems.COFFEE_BEANS
        );

        // 65% chance
        registerCompostableItems(0.65f,
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.WILD_COFFEE,
                ModItems.WILD_COTTON,
                ModItems.WILD_BELL_PEPPERS
        );

        // 85% chance
        registerCompostableItems(0.85f,
                ModItems.CHERRY_BLOSSOM_COOKIE,
                ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE
        );

        // 100% chance
        registerCompostableItems(1.0f,
                ModItems.CHERRY_BLOSSOM_CHEESECAKE
        );
    }
}
