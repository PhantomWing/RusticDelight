package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_WILD_COTTON = registerKey("add_wild_cotton");
    public static final ResourceKey<BiomeModifier> ADD_WILD_BELL_PEPPERS = registerKey("add_wild_bell_peppers");
    public static final ResourceKey<BiomeModifier> ADD_WILD_COFFEE = registerKey("add_wild_coffee");
    public static final ResourceKey<BiomeModifier> ADD_BELL_PEPPER_BLOCK_PATCH = registerKey("add_bell_pepper_block_patch");

    public static void bootstrap(BootstapContext<BiomeModifier> context){
        registerWildCrops(context);
    }

    private static void registerWildCrops(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_COTTON, ModPlacedFeatures.WILD_COTTON_PLACED_KEY, ModTags.Biomes.HAS_WILD_COTTON);
        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_BELL_PEPPERS, ModPlacedFeatures.WILD_BELL_PEPPERS_PLACED_KEY, ModTags.Biomes.HAS_WILD_BELL_PEPPERS);
        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_WILD_COFFEE, ModPlacedFeatures.WILD_COFFEE_PLACED_KEY, ModTags.Biomes.HAS_WILD_COFFEE);
        registerWithBiomeFilter(context, placedFeatures, biomes, ADD_BELL_PEPPER_BLOCK_PATCH, ModPlacedFeatures.BELL_PEPPER_BLOCK_PATCH_PLACED_KEY, ModTags.Biomes.HAS_BELL_PEPPER_BLOCK_PATCH);
    }

    private static void registerWithBiomeFilter(BootstapContext<BiomeModifier> context, HolderGetter<PlacedFeature> placedFeatures, HolderGetter<Biome> biomes, ResourceKey<BiomeModifier> biomeModifierKey, ResourceKey<PlacedFeature> placedFeatureKey, TagKey<Biome> biomeTag) {
        context.register(biomeModifierKey, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(biomeTag),
                HolderSet.direct(placedFeatures.getOrThrow(placedFeatureKey)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(RusticDelight.MOD_ID, name));
    }
}
