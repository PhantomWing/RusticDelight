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

    /**
     * Adds Rustic Delight's edible crops to the villager food map so farmer villagers count, share and
     * breed on them like vanilla crops ({@code Villager.FOOD_POINTS} is otherwise hardcoded to
     * bread/potato/carrot/beetroot). Copies the current map first so additions from other mods are
     * preserved instead of clobbered.
     */
    private static void registerFoodPoints() {
        HashMap<Item, Integer> foodPoints = new HashMap<>(Villager.FOOD_POINTS);
        foodPoints.put(ModItems.BELL_PEPPER_GREEN, 1);
        foodPoints.put(ModItems.BELL_PEPPER_YELLOW, 1);
        foodPoints.put(ModItems.BELL_PEPPER_RED, 1);
        foodPoints.put(ModItems.BELL_PEPPER_ORANGE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_WHITE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_PINK, 1);
        foodPoints.put(ModItems.BELL_PEPPER_BLUE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_PURPLE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_BLACK, 1);
        // Cotton and coffee aren't truly food, but counting them (value 1) lets farmer villagers reliably
        // offload them to a partner so they work in automatic farms. The minor realism cost (villagers eating /
        // breeding on them) is unnoticeable in normal play.
        foodPoints.put(ModItems.COTTON_BOLL, 1);
        foodPoints.put(ModItems.COFFEE_BEANS, 1);
        Villager.FOOD_POINTS = foodPoints;
    }
}
