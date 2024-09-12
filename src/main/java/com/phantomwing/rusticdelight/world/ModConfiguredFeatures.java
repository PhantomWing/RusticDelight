package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_WILD_COTTON_KEY = registerKey("wild_cotton");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        registerFlowers(context);
    }

    private static void registerFlowers(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, OVERWORLD_WILD_COTTON_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        32,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_COTTON.get())))));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
