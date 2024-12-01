package com.phantomwing.rusticdelight.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;

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
                ModItems.COTTON_BOLL,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COTTON_SEEDS,
                ModItems.COFFEE_BEANS
        );
    }
}