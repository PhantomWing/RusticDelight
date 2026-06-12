package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;

public class ModConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COTTON_KEY = registerKey("wild_cotton");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_BELL_PEPPERS_KEY = registerKey("wild_bell_peppers");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COFFEE_KEY = registerKey("wild_coffee");
    public static ResourceKey<ConfiguredFeature<?, ?>> BELL_PEPPER_BLOCK_PATCH_KEY = registerKey("bell_pepper_block_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        registerWildCrops(context);
        registerBellPepperBlockPatch(context, BELL_PEPPER_BLOCK_PATCH_KEY, 48, 7, 3);
    }

    private static void registerWildCrops(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerFlowerPatch(context, WILD_COTTON_KEY, ModBlocks.WILD_COTTON.get(), 32, 6, 4);
        registerFlowerPatch(context, WILD_BELL_PEPPERS_KEY, ModBlocks.WILD_BELL_PEPPERS.get(), 48, 4, 4);
        registerFlowerPatch(context, WILD_COFFEE_KEY, ModBlocks.WILD_COFFEE.get(), 48, 6, 4);
    }

    // A melon-like patch of randomly red/yellow/green bell pepper blocks.
    private static void registerBellPepperBlockPatch(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, int tries, int xzSpread, int ySpread) {
        WeightedStateProvider provider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(ModBlocks.BELL_PEPPER_RED_BLOCK.get().defaultBlockState(), 1)
                        .add(ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get().defaultBlockState(), 1)
                        .add(ModBlocks.BELL_PEPPER_GREEN_BLOCK.get().defaultBlockState(), 1)
                        .build()
        );
        register(context, key, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        tries,
                        xzSpread,
                        ySpread,
                        // Melon-style: only place where the spot is replaceable and the block below is grass.
                        PlacementUtils.filtered(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(provider),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK)
                                )
                        )
                )
        );
    }

    private static void registerFlowerPatch(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Block block, int tries, int xzSpread, int ySpread) {
        register(context, key, Feature.FLOWER,
                new RandomPatchConfiguration(
                        tries,
                        xzSpread,
                        ySpread,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(block))
                        )
                )
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
