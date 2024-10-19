package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_WILD_COTTON = registerKey("add_wild_cotton");
    public static final ResourceKey<BiomeModifier> ADD_WILD_BELL_PEPPERS = registerKey("add_wild_bell_peppers");
    public static final ResourceKey<BiomeModifier> ADD_WILD_COFFEE = registerKey("add_wild_coffee");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        registerWildCrops(context);
    }

    private static void registerWildCrops(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_COTTON, ModPlacedFeatures.WILD_COTTON_PLACED_KEY, BiomeTags.IS_FOREST);
        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_BELL_PEPPERS, ModPlacedFeatures.WILD_BELL_PEPPERS_PLACED_KEY, BiomeTags.IS_JUNGLE);
        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_COFFEE, ModPlacedFeatures.WILD_COFFEE_PLACED_KEY, BiomeTags.IS_JUNGLE);
    }

    private static void registerWithBiomeFilter(BootstrapContext<BiomeModifier> context, HolderGetter<PlacedFeature> placedFeatures, HolderGetter<Biome> biomes, ResourceKey<BiomeModifier> biomeModifierKey, ResourceKey<PlacedFeature> placedFeatureKey, TagKey<Biome> biomeTag) {
        context.register(biomeModifierKey, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(biomeTag),
                HolderSet.direct(placedFeatures.getOrThrow(placedFeatureKey)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }
}
