package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGeneration {
    public static final RegistryKey<PlacedFeature> WILD_BELL_PEPPERS_PLACED = registerKey("wild_bell_peppers_placed");
    public static final RegistryKey<PlacedFeature> WILD_COTTON_PLACED = registerKey("wild_cotton_placed");
    public static final RegistryKey<PlacedFeature> WILD_COFFEE_PLACED = registerKey("wild_coffee_placed");
    public static final RegistryKey<PlacedFeature> BELL_PEPPER_BLOCK_PATCH_PLACED = registerKey("bell_pepper_block_patch_placed");

    // Data-driven biome lists (data/rusticdelight/tags/worldgen/biome/) so packs can retarget them.
    public static final TagKey<Biome> HAS_WILD_COTTON = biomeTag("has_wild_cotton");
    public static final TagKey<Biome> HAS_WILD_BELL_PEPPERS = biomeTag("has_wild_bell_peppers");
    public static final TagKey<Biome> HAS_WILD_COFFEE = biomeTag("has_wild_coffee");
    public static final TagKey<Biome> HAS_BELL_PEPPER_BLOCK_PATCH = biomeTag("has_bell_pepper_block_patch");

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RusticDelight.MOD_ID, name));
    }

    private static TagKey<Biome> biomeTag(String name) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(RusticDelight.MOD_ID, name));
    }

    public static void registerModWorldGeneration() {
        RusticDelight.LOGGER.info("Registering biome modifications for " + RusticDelight.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_WILD_COTTON), GenerationStep.Feature.VEGETAL_DECORATION, WILD_COTTON_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_WILD_BELL_PEPPERS), GenerationStep.Feature.VEGETAL_DECORATION, WILD_BELL_PEPPERS_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_WILD_COFFEE), GenerationStep.Feature.VEGETAL_DECORATION, WILD_COFFEE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_BELL_PEPPER_BLOCK_PATCH), GenerationStep.Feature.VEGETAL_DECORATION, BELL_PEPPER_BLOCK_PATCH_PLACED);
    }
}
