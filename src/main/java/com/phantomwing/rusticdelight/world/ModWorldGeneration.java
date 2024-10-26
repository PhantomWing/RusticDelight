package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGeneration {
    public static final RegistryKey<PlacedFeature> WILD_BELL_PEPPERS_PLACED = registerKey("wild_bell_peppers_placed");
    public static final RegistryKey<PlacedFeature> WILD_COTTON_PLACED = registerKey("wild_cotton_placed");
    public static final RegistryKey<PlacedFeature> WILD_COFFEE_PLACED = registerKey("wild_coffee_placed");

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RusticDelight.MOD_ID, name));
    }

    public static void registerModWorldGeneration() {
        RusticDelight.LOGGER.info("Registering biome modifications for " + RusticDelight.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, WILD_COTTON_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, WILD_BELL_PEPPERS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, WILD_COFFEE_PLACED);
    }
}
