package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void registerModWorldGeneration() {
        RusticDelight.LOGGER.info("Registering biome modifications for " + RusticDelight.MOD_ID);

        BiomeModifications.addFeature(
                BiomeSelectors.tag(ModTags.Biomes.HAS_WILD_COTTON),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILD_COTTON_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(ModTags.Biomes.HAS_WILD_BELL_PEPPERS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILD_BELL_PEPPERS_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(ModTags.Biomes.HAS_WILD_COFFEE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILD_COFFEE_PLACED_KEY
        );
    }
}
