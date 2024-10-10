package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.filter.BiomeTagFilter;

import java.util.List;

public class WildCropGeneration {
    public static Holder<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_COTTON;
    public static Holder<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_BELL_PEPPERS;

    public static Holder<PlacedFeature> PATCH_WILD_COTTON;
    public static Holder<PlacedFeature> PATCH_WILD_BELL_PEPPERS;

    public static final BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);

    public static void registerWildCropGeneration() {
        FEATURE_PATCH_WILD_COTTON = registerConfiguredFeature("wild_cotton",
                randomPatchConfig(ModBlocks.WILD_COTTON.get(), 32, 6, 2, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT)));
        FEATURE_PATCH_WILD_BELL_PEPPERS = registerConfiguredFeature("wild_bell_peppers",
                randomPatchConfig(ModBlocks.WILD_BELL_PEPPERS.get(), 32, 4, 4, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT)));

        PATCH_WILD_COTTON = registerPlacedFeature("wild_cotton_placed",
                FEATURE_PATCH_WILD_COTTON, RarityFilter.onAverageOnceEvery(Configuration.CHANCE_WILD_COTTON.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), BiomeTagFilter.biomeIsInTag(BiomeTags.IS_FOREST));
        PATCH_WILD_BELL_PEPPERS = registerPlacedFeature("wild_bell_peppers_placed",
                FEATURE_PATCH_WILD_BELL_PEPPERS, RarityFilter.onAverageOnceEvery(Configuration.CHANCE_WILD_BELL_PEPPERS.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), BiomeTagFilter.biomeIsInTag(BiomeTags.IS_JUNGLE));
    }

    public static RandomPatchConfiguration randomPatchConfig(Block block, int tries, int xSpread, int ySpread, BlockPredicate plantedOn) {
        return new RandomPatchConfiguration(tries, xSpread, ySpread, PlacementUtils.filtered(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)),
                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn)));
    }

    static Holder<PlacedFeature> registerPlacedFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return BuiltinRegistries.register(
                BuiltinRegistries.PLACED_FEATURE,
                new ResourceLocation(RusticDelight.MOD_ID, name),
                new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
    }

    private static Holder<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name, RandomPatchConfiguration configuration) {
        return BuiltinRegistries.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                new ResourceLocation(RusticDelight.MOD_ID, name),
                new ConfiguredFeature<>(ModBiomeFeatures.WILD_RICE.get(), configuration)
        );
    }
}
