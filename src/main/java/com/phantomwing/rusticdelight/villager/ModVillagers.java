package com.phantomwing.rusticdelight.villager;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.Item;

import java.util.HashMap;

/**
 * Runtime villager hooks. Trade registration is now data-driven (see
 * {@code com.phantomwing.rusticdelight.datagen.ModVillagerTrades}); this class only handles the
 * food-points map that controls which items farmers can pick up and consume.
 */
public class ModVillagers {
    public static void registerFoodsAndTrades() {
        registerFoodPoints();
    }

    private static void registerFoodPoints() {
        HashMap<Item, Integer> foodPoints = new HashMap<>(Villager.FOOD_POINTS);
        foodPoints.put(ModItems.COTTON_BOLL, 1);
        foodPoints.put(ModItems.BELL_PEPPER_GREEN, 1);
        foodPoints.put(ModItems.BELL_PEPPER_YELLOW, 1);
        foodPoints.put(ModItems.BELL_PEPPER_RED, 1);
        foodPoints.put(ModItems.COFFEE_BEANS, 1);
        Villager.FOOD_POINTS = foodPoints;
    }
}
