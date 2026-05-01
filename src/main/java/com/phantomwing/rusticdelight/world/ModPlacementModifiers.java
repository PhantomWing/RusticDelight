package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class ModPlacementModifiers {
    public static final PlacementModifierType<ConfigurableRarityFilter> CONFIGURABLE_RARITY_FILTER = registerModifier("configurable_rarity_filter",
            () -> ConfigurableRarityFilter.MODIFIER_CODEC);

    public static <T extends net.minecraft.world.gen.placementmodifier.PlacementModifier> PlacementModifierType<T> registerModifier(String name, PlacementModifierType<T> type) {
        return Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, Identifier.of(RusticDelight.MOD_ID, name), type);
    }

    public static void registerPlacementModifiers() {
        RusticDelight.LOGGER.info("Registering placement modifiers for " + RusticDelight.MOD_ID);
    }
}
