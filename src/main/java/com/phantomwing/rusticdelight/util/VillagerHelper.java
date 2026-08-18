package com.phantomwing.rusticdelight.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VillagerHelper {
    public static void addGatherableItems(Item... itemsToAdd) {
        Set<Item> newWantedItems = Sets.newHashSet(itemsToAdd);
        newWantedItems.addAll(VillagerEntity.GATHERABLE_ITEMS);

        VillagerEntity.GATHERABLE_ITEMS = ImmutableSet.copyOf(newWantedItems);
    }

    public static void registerGatherableItems() {
        addGatherableItems(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE,
                ModItems.BELL_PEPPER_WHITE,
                ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE,
                ModItems.BELL_PEPPER_PURPLE,
                ModItems.BELL_PEPPER_BLACK,
                ModItems.COTTON_BOLL,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.PALE_BELL_PEPPER_SEEDS,
                ModItems.DARK_BELL_PEPPER_SEEDS,
                ModItems.COTTON_SEEDS,
                ModItems.COFFEE_BEANS
        );
    }

    /**
     * Adds Rustic Delight's edible crops to the villager food map so farmer villagers count, share and
     * breed on them like vanilla crops ({@code VillagerEntity.ITEM_FOOD_VALUES} is otherwise hardcoded to
     * bread/potato/carrot/beetroot). Copies the current map first so additions from other mods are
     * preserved instead of clobbered.
     */
    public static void registerVillagerFood() {
        Map<Item, Integer> newFoodPoints = new HashMap<>(VillagerEntity.ITEM_FOOD_VALUES);
        newFoodPoints.put(ModItems.BELL_PEPPER_GREEN, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_YELLOW, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_RED, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_ORANGE, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_WHITE, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_PINK, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_BLUE, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_PURPLE, 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_BLACK, 1);
        // Cotton and coffee aren't truly food, but counting them (value 1) lets farmer villagers reliably
        // offload them to a partner so they work in automatic farms. The minor realism cost (villagers eating /
        // breeding on them) is unnoticeable in normal play.
        newFoodPoints.put(ModItems.COTTON_BOLL, 1);
        newFoodPoints.put(ModItems.COFFEE_BEANS, 1);
        VillagerEntity.ITEM_FOOD_VALUES = ImmutableMap.copyOf(newFoodPoints);
    }
}
