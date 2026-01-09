package com.phantomwing.rusticdelight.world;

import com.mojang.serialization.MapCodec;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class ModPlacementModifiers {
    public static final PlacementModifierType<ConfigurableRarityFilter> CONFIGURABLE_RARITY_FILTER = registerModifier("configurable_rarity_filter", typeConvert(ConfigurableRarityFilter.CODEC));

    private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(MapCodec<P> codec) {
        return () -> codec;
    }

    public static <T extends PlacementModifier> PlacementModifierType<T>  registerModifier(String name, PlacementModifierType<T> typeConvert) {
        return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name), typeConvert);
    }

    public static void registerPlacementModfiiers() {
        RusticDelight.LOGGER.info("Registering placement modifiers for " + RusticDelight.MOD_ID);

    }
}
