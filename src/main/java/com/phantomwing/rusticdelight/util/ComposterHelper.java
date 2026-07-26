package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class ComposterHelper {
    private static void registerCompostableItems (float chance, ItemLike...items) {
        for (ItemLike item : items) {
            ComposterBlock.COMPOSTABLES.put(item, chance);
        }
    }

    public static void registerCompostableItems() {
        // 30% chance
        registerCompostableItems(0.3f,
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS
        );

        // 50% chance
        registerCompostableItems(0.5f,
            ModItems.COTTON_BOLL,
            ModItems.POTATO_SLICES,
            ModItems.COFFEE_BEANS,
            ModItems.BELL_PEPPER_SLICE_GREEN,
            ModItems.BELL_PEPPER_SLICE_YELLOW,
            ModItems.BELL_PEPPER_SLICE_RED,
            ModItems.BELL_PEPPER_SLICE_ORANGE,
            ModItems.BELL_PEPPER_SLICE_WHITE,
            ModItems.BELL_PEPPER_SLICE_PINK,
            ModItems.BELL_PEPPER_SLICE_BLUE,
            ModItems.BELL_PEPPER_SLICE_PURPLE,
            ModItems.BELL_PEPPER_SLICE_BLACK
        );

        // 65% chance
        registerCompostableItems(0.65f,
            ModItems.BELL_PEPPER_GREEN,
            ModItems.BELL_PEPPER_YELLOW,
            ModItems.BELL_PEPPER_RED,
            ModItems.BELL_PEPPER_ORANGE,
            ModItems.BELL_PEPPER_WHITE,
            ModItems.BELL_PEPPER_PINK,
            ModItems.BELL_PEPPER_BLUE,
            ModItems.BELL_PEPPER_PURPLE,
            ModItems.BELL_PEPPER_BLACK,
            ModItems.BELL_PEPPER_GREEN_BLOCK,
            ModItems.BELL_PEPPER_YELLOW_BLOCK,
            ModItems.BELL_PEPPER_RED_BLOCK,
            ModItems.BELL_PEPPER_ORANGE_BLOCK,
            ModItems.BELL_PEPPER_WHITE_BLOCK,
            ModItems.BELL_PEPPER_PINK_BLOCK,
            ModItems.BELL_PEPPER_BLUE_BLOCK,
            ModItems.BELL_PEPPER_PURPLE_BLOCK,
            ModItems.BELL_PEPPER_BLACK_BLOCK,
            ModItems.WILD_COFFEE,
            ModItems.WILD_COTTON,
            ModItems.WILD_BELL_PEPPERS,
            ModItems.WILD_PALE_BELL_PEPPERS,
            ModItems.WILD_DARK_BELL_PEPPERS,
            ModItems.ROASTED_COFFEE_BEANS
        );

        // 85% chance
        registerCompostableItems(0.85f,
            ModItems.COFFEE_COOKIE,
            ModItems.SYRUP_COOKIE,
            ModItems.CHERRY_BLOSSOM_COOKIE,
            ModItems.SYRUP_CHEESECAKE_SLICE,
            ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE,
            ModItems.COFFEE_CHEESECAKE_SLICE
        );

        // 100% chance
        registerCompostableItems(1.0f,
            ModItems.SYRUP_CHEESECAKE,
            ModItems.CHERRY_BLOSSOM_CHEESECAKE,
            ModItems.COFFEE_CHEESECAKE
        );
    }
}
