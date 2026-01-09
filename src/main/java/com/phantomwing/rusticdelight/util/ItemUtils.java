package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class ItemUtils {
    public static String getName(Item item) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }

    public static ResourceLocation getResourceLocation(String modId, Item item) {
        return ResourceLocation.fromNamespaceAndPath(modId, "item/" + getName(item));
    }

    public static ResourceLocation getResourceLocation(Item item) {
        return getResourceLocation(RusticDelight.MOD_ID, item);
    }
}
