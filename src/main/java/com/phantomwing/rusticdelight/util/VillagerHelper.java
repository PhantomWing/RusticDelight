package com.phantomwing.rusticdelight.util;

import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.mixin.content.registry.VillagerEntityAccessor;
import net.minecraft.item.Item;

import java.util.Set;

public class VillagerHelper {
    private static Set<Item> getNewGatherableItems () {
        return Sets.newHashSet(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.COTTON_BOLL,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COTTON_SEEDS,
                ModItems.COFFEE_BEANS);
    }

    public static void addGatherableItems(Set<Item> itemsToAdd) {
        Set<Item> gatherableItems = VillagerEntityAccessor.fabric_getGatherableItems();
        gatherableItems.addAll(itemsToAdd);
        VillagerEntityAccessor.fabric_setGatherableItems(gatherableItems);
    }

    public static void registerGatherableItems() {
        addGatherableItems(getNewGatherableItems());
    }
}
