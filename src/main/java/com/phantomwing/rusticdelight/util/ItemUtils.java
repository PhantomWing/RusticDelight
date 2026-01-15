package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class ItemUtils {
    public static String getName(Item item) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }

    public static Identifier getIdentifier(String modId, Item item) {
        return Identifier.fromNamespaceAndPath(modId, "item/" + getName(item));
    }

    public static Identifier getIdentifier(Item item) {
        return getIdentifier(RusticDelight.MOD_ID, item);
    }
}
