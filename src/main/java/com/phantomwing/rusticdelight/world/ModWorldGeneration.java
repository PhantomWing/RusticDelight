package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModWorldGeneration {
    public static final ResourceKey<PlacedFeature> WILD_BELL_PEPPERS_PLACED = registerKey("wild_bell_peppers_placed");
    public static final ResourceKey<PlacedFeature> WILD_COTTON_PLACED = registerKey("wild_cotton_placed");
    public static final ResourceKey<PlacedFeature> WILD_COFFEE_PLACED = registerKey("wild_coffee_placed");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }

    public static void registerModWorldGeneration() {
        RusticDelight.LOGGER.info("Registering biome modifications for " + RusticDelight.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, WILD_COTTON_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Decoration.VEGETAL_DECORATION, WILD_BELL_PEPPERS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Decoration.VEGETAL_DECORATION, WILD_COFFEE_PLACED);
    }
}
