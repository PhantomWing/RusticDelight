package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class FuelHelper {
    public static void registerFuelItems() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.COTTON_BOLL, 100);
        });
    }
}
