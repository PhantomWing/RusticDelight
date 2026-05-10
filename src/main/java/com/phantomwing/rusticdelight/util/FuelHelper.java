package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class FuelHelper {
    public static void registerFuelItems() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.COTTON_BOLL, 100);
        });
    }
}
