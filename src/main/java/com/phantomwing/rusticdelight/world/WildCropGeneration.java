package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

@SuppressWarnings("unused")
public class WildCropGeneration
{
    // Those are unused, but kept for reference just in case
    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_COTTON = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "patch_wild_cotton"));

    public static ResourceKey<PlacedFeature> PATCH_WILD_COTTON = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "patch_wild_cotton"));

    public static void load() {
    }
}